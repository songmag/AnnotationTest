package me.songJe;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.SOURCE)
/**
 * Assignment Numbering
 * @author songm
 *
 */
public @interface AssignMentNumber {
	int[] number() default {0};
}
