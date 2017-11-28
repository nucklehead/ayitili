package com.colorofhope.ayitili.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface BootstrapHtmlDisplay {
    String HTML_TEXT_DIV = "<div>%s</div>";
    String HTML_IMAGE_DIV = "<img src=\"/images/%1$s.jpg\" width=\"100\"/>";
    String HTML_PARAGRAPH_DIV = "<p>%s</p>";
    String HTML_IGNORE_DIV = "HTML_IGNORE_DIV";


    String value();
}
