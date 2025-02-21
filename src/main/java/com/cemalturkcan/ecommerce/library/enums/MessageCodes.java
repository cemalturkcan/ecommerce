package com.cemalturkcan.ecommerce.library.enums;

import lombok.Getter;

@Getter
public enum MessageCodes {
    SUCCESS("200","general.success"),
    FAIL("500","general.fail"),
    BAD_REQUEST("400","general.badRequest"),
    NOT_FOUND("404","general.notFound"),
    UNAUTHORIZED("401","general.unauthorized"),
    ENTITY_NOT_FOUND("404","general.entityNotFound"),
    ENTITY_ALREADY_EXISTS("409","general.entityAlreadyExists"),

    WRONG_CREDENTIALS("1005","general.wrongCreds"),

    DATA_INTEGRITY_VIOLATION("1003","general.dataIntegrityViolation"),
    METHOD_NOT_ALLOWED("405","general.methodNotAllowed");

    private final String code;
    private final String message;

    MessageCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
