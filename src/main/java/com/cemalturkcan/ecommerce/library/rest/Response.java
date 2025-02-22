package com.cemalturkcan.ecommerce.library.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private T data;
    private MetaResponse meta;

    public Response(MetaResponse meta) {
        this.meta = meta;
    }

    public Response(T data) {
        this.data = data;
        this.meta = MetaResponse.success();
    }

    @Override
    public String toString() {
        return "data: " + (data != null ? data.toString() : "null") +
                ", meta: " +
                (meta != null ? meta.toString() : "null");
    }

}
