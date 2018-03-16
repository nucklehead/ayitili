package com.colorofhope.ayitili.model;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SearchResponseIgnore {
    boolean value() default true;
}
