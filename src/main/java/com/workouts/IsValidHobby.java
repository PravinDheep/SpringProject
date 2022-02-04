package com.workouts;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.annotation.HttpConstraint;

import org.springframework.messaging.handler.annotation.Payload;

@Documented
//@Constraint(value = HobbyValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidHobby {
	String message() default "Accepted hobbies are: " + "Football, Music, Cricket and Hockey";

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
