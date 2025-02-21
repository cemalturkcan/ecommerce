package com.cemalturkcan.ecommerce.library.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class SameAsValidator implements ConstraintValidator<SameAs, Object> {
    private String field;

    @Override
    public void initialize(SameAs constraintAnnotation) {
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Eğer alan boşsa, zaten @NotBlank kontrol edecektir.
        }

        try {
            // ConstraintValidatorContext içindeki root nesnesini al
            Object rootObject = getRootObject(context);
            if (rootObject == null) {
                return true; // Eğer nesne bulunamazsa devam et
            }

            // Şifre alanını bul ve değerini oku
            Field passwordField = rootObject.getClass().getDeclaredField(field);
            passwordField.setAccessible(true);
            Object passwordValue = passwordField.get(rootObject);

            return value.equals(passwordValue);
        } catch (Exception e) {
            return false; // Alan bulunamazsa veya hata olursa doğrulama başarısız olur.
        }
    }

    /**
     * ConstraintValidatorContext içinden root nesneyi elde etmek için
     * bir workaround olarak 'RootBean' nesnesine erişiyoruz.
     */
    private Object getRootObject(ConstraintValidatorContext context) {
        try {
            Field rootBeanField = context.getClass().getDeclaredField("rootBean");
            rootBeanField.setAccessible(true);
            return rootBeanField.get(context);
        } catch (Exception e) {
            return null;
        }
    }
}
