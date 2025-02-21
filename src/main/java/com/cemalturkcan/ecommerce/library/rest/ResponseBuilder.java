package com.cemalturkcan.ecommerce.library.rest;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> Response<T> build(T item) {
        return new Response<>(item);
    }

    public static Response<MetaResponse> build(MetaResponse metaResponse) {
        return new Response<>(metaResponse);
    }

    public static Response<Void> buildForbiddenResponse() {
        return new Response<>(new MetaResponse("403", "Forbidden"));
    }

    public static Response<Void> buildUnauthorizedResponse() {
        return new Response<>(new MetaResponse("401", "Unauthorized"));
    }

    public static Response<Void> buildSuccessResponse() {
        return new Response<>(new MetaResponse("200", "SUCCESS"));
    }
}
