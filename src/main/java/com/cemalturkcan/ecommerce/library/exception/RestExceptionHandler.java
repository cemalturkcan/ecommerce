package com.cemalturkcan.ecommerce.library.exception;

import com.cemalturkcan.ecommerce.library.enums.MessageCodes;
import com.cemalturkcan.ecommerce.library.rest.MetaResponse;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(CoreException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<MetaResponse> handleCoreException(CoreException coreException, Locale locale) {
        MessageCodes messageCode = coreException.getCode();
        String message = messageSource.getMessage(messageCode.getMessage(), coreException.getArgs(), locale);
        String sb = "[CoreException] messageCode: " + messageCode.getCode() +
                " , message: " +
                message;
        log.error(sb);
        return ResponseBuilder.build(MetaResponse.of(messageCode.getCode(), message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<MetaResponse> handleValidationErrors(MethodArgumentNotValidException ex, Locale locale) {
        String error = ex.getBindingResult().getFieldErrors()
                .stream().findFirst().map(FieldError::getDefaultMessage).orElse(MessageCodes.BAD_REQUEST.getMessage());
        String message = messageSource.getMessage(error, null, locale);
        String sb = "[MethodArgumentNotValidException] code: " + MessageCodes.BAD_REQUEST +
                " , message: " +
                message;
        log.error(sb);
        return ResponseBuilder.build((MetaResponse.of(MessageCodes.BAD_REQUEST.getCode(), message)));
    }

}
