package com.cemalturkcan.ecommerce.library.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Status {

    ACTIVE(1),
    PASSIVE(0),
    DELETED(-1);

    private final Integer value;

    Status(Integer value) {
        this.value = value;
    }

    public static Status of(Integer value) {
        return Stream.of(Status.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

    public static Status of(String value) {
        return Stream.of(Status.values())
                .filter(status -> status.name().equals(value)).findFirst().orElseThrow();
    }
}