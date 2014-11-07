package com.foobnix.android.utils.res;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.foobnix.android.utils.ModelFragment;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExtraArgument_2 {
    String value() default ModelFragment.EXTRA_ARGUMENT_2;
}
