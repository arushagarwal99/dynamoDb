package com.thrivent.core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Retention(value = CLASS)
@Target(value = {METHOD, FIELD, PARAMETER, LOCAL_VARIABLE, TYPE_USE})
public @interface Nullable {
}