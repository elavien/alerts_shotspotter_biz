package test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by SvetLana on 01.07.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SetTestName {
    int idx() default 0;
}