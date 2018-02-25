package com.colorofhope.ayitili.model;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_IGNORE_DIV;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.annotation.Id;

public abstract class DBModel {
  @BootstrapHtmlDisplay(HTML_IGNORE_DIV)
  @BootstrapLabel("id")
  @Id
  public String id;

  public List<ClassField> classToBootstrapForm(Boolean showAllInForm) {
    Stream<ClassField> mapping =
        Arrays.stream(this.getClass().getFields())
            .map(
                field ->
                    new ClassField(
                        field.getName(),
                        field.getGenericType(),
                        field.getAnnotation(BootstrapLabel.class) == null
                            ? field.getName()
                            : field.getAnnotation(BootstrapLabel.class).value(),
                        field.getAnnotation(BootstrapLabel.class) == null
                            || (showAllInForm
                                || field.getAnnotation(BootstrapLabel.class).showInForm())));
    return mapping.collect(Collectors.toList());
  }

  public List<ClassField> classToBootstrapDisplay() {
    Stream<Field> annotatedFields = findBootstrapHtmlDisplayField();
    Stream<ClassField> mapping =
        annotatedFields.map(
            field -> {
              try {
                return new ClassField(field, field.get(this));
              } catch (IllegalAccessException e) {
                return null;
              }
            });
    return mapping.collect(Collectors.toList());
  }

  public List<String> findBootstrapHtmlDisplayFieldNames() {
    Stream<Field> annotatedFields = findBootstrapHtmlDisplayField();
    Stream<String> annotatedFieldNames =
        annotatedFields.map(field -> field.getAnnotation(BootstrapLabel.class).value());
    return annotatedFieldNames.collect(Collectors.toList());
  }

  public Stream<Field> findBootstrapHtmlDisplayField() {
    return Arrays.stream(this.getClass().getFields())
        .filter(
            field ->
                field.getAnnotation(BootstrapHtmlDisplay.class) != null
                    && !field
                        .getAnnotation(BootstrapHtmlDisplay.class)
                        .value()
                        .equals(HTML_IGNORE_DIV));
  }

  public String toJSON() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

  public void merge(DBModel that) {
    Arrays.stream(this.getClass().getFields())
        .forEach(
            field -> {
              try {
                if (field.get(that) != null) {
                  try {
                    field.set(this, field.get(that));
                  } catch (IllegalAccessException e) {
                    e.printStackTrace();
                  }
                }
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              }
            });
  }
}
