package com.foo.movies.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Instance per activity
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

