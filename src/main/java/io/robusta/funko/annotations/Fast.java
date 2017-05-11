package io.robusta.funko.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Nicolas Zozol on 05/04/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Fast {

    int speed() default 10;
    String value() default "Car";

}
