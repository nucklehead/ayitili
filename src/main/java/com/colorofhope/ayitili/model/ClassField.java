package com.colorofhope.ayitili.model;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_IGNORE_DIV;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.multipart.MultipartFile;

public class ClassField {

  private static String HTML_ID =
      "<div class=\"form-group\">\n"
          + "<input type=\"hidden\" class=\"form-control\" name=\"%1$s\" id=\"%1$s\" disabled/>\n"
          + "</div>";

  private static String HTML_STRING =
      "<div class=\"form-group\">\n"
          + "<label class=\"col-form-label\" for=\"%1$s\">%2$s: </label>\n"
          + "<input type=\"text\" class=\"form-control\" name=\"%1$s\" id=\"%1$s\"/>\n"
          + "</div>";

  private static String HTML_LONG =
      "<div class=\"form-group\">\n"
          + "<label class=\"col-form-label\" for=\"%1$s\">%2$s: </label>\n"
          + "<input type=\"text\" class=\"form-control\" name=\"%1$s\" id=\"%1$s\"/>\n"
          + "</div>";

  private static String HTML_BOOLEAN =
      "<div class=\"form-check\">\n"
          + "<label class=\"form-check-label\" th:text=\"%2$s\">\n"
          + "<input type=\"checkbox\" class=\"form-check-input\" name=\"%1$s\" id=\"%1$s\"/>\n"
          + "</label>\n"
          + "</div>";

  private static String HTML_ENUM =
      "<div class=\"form-group\">\n"
          + "<label class=\"col-form-label\" for=\"%1$s\">%2$s: </label>\n"
          + "<select class=\"form-control\" name=\"%1$s\" id=\"%1$s\">\n"
          + "%3$s"
          + "</select>\n"
          + "</div>";
  private static String HTML_ENUM_OPTION = "<option th:value=\"%1$s\">%2$s</option>\n";

  private static String HTML_DATE =
      "<div class=\"input-group date\">\n"
          + "<label class=\"col-form-label\" for=\"%1$s\">%2$s</label>\n"
          + "<input type=\"text\" class=\"form-control\" name=\"%1$s\" id=\"%1$s\"/>\n"
          + "<div class=\"input-group-addon\">\n"
          + "<span class=\"glyphicon glyphicon-th\"></span>\n"
          + "</div>\n"
          + "</div>";

  private static String HTML_FILE =
      "<div class=\"form-group\">\n"
          + "<label class=\"col-form-label\" for=\"%1$s\">%2$s: </label>\n"
          + "<input type=\"file\" class=\"file\" name=\"%1$s\" id=\"%1$s\" data-show-upload=\"false\" data-show-caption=\"true\" data-msg-placeholder=\"Chwazi imaj\"/>\n"
          + "</div>";

  private static Map<String, String> classToHtmlMap = new HashMap<>();

  static {
    classToHtmlMap.put(String.class.getName(), HTML_STRING);
    classToHtmlMap.put("id", HTML_ID);
    classToHtmlMap.put(Long.class.getName(), HTML_LONG);
    classToHtmlMap.put(Duration.class.getName(), HTML_LONG);
    classToHtmlMap.put(Boolean.class.getName(), HTML_BOOLEAN);
    classToHtmlMap.put("Enum", HTML_ENUM);
    classToHtmlMap.put(Date.class.getName(), HTML_DATE);
    classToHtmlMap.put(MultipartFile.class.getName(), HTML_FILE);
  }

  public String name;
  public String htmlCode;

  public ClassField(String name, Class type, String bootstrapLabel) {
    this.name = name;

    if (classToHtmlMap.get(name) != null) {
      htmlCode = String.format(classToHtmlMap.get(name), name);
    } else if (classToHtmlMap.get(type.getName()) == null) {
      this.htmlCode = null;
    } else if (type.isEnum()) {
      Stream<String> options =
          Arrays.stream(type.getEnumConstants())
              .map(
                  enumValue -> {
                    BootstrapLabel bootstrapOptionLabel = null;
                    try {
                      bootstrapOptionLabel =
                          type.getField(enumValue.toString()).getAnnotation(BootstrapLabel.class);
                    } catch (NoSuchFieldException e) {
                      e.printStackTrace();
                    }
                    return String.format(
                        HTML_ENUM_OPTION,
                        enumValue,
                        bootstrapOptionLabel == null ? enumValue : bootstrapOptionLabel.value());
                  });
      String optionHtml = String.join("", options.collect(Collectors.toList()));
      this.htmlCode = String.format(classToHtmlMap.get("Enum"), name, bootstrapLabel, optionHtml);
    } else {
      this.htmlCode = String.format(classToHtmlMap.get(type.getName()), name, bootstrapLabel);
    }
  }

  public ClassField(Field field, Object object) {
    this.name = field.getName();
    BootstrapHtmlDisplay bootstrapHtmlDisplay = field.getAnnotation(BootstrapHtmlDisplay.class);
    String htmlTemplate =
        bootstrapHtmlDisplay == null ? HTML_IGNORE_DIV : bootstrapHtmlDisplay.value();
    String value = object.toString();
    this.htmlCode = String.format(htmlTemplate, value);
  }
}
