package com.example.cosmocats.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CosmicWordCheckValidator.class)
public @interface CosmicWordCheck {

    String message() default "Name must contain a cosmic word like 'star', 'galaxy', or 'comet'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

