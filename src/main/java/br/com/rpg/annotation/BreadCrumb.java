package br.com.rpg.annotation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Documented
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface BreadCrumb {

	String menu();

	String controller();
}