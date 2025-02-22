package com.cemalturkcan.ecommerce.library.exception;

import com.cemalturkcan.ecommerce.library.enums.MessageCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CoreException extends RuntimeException {

    private final MessageCodes code;
    private final String message;
    @Setter
    private Object[] args;

    public CoreException(MessageCodes code, Object... args) {
        this.code = code;
        this.message = null;
        this.args = args;
    }

    public CoreException(MessageCodes code, String message) {
        this.code = code;
        this.message = message;
        this.args = new Object[0];
    }

}
