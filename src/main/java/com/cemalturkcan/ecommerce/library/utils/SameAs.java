package com.cemalturkcan.ecommerce.library.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SameAsValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SameAs {
    String message() default "{validation.sameAs}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String field(); // Karşılaştırılacak alanı belirtiyoruz
}
