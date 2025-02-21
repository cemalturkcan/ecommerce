package com.cemalturkcan.ecommerce.domain.user.customer.web;

import java.io.Serializable;
import java.util.Date;

public record CustomerResponse(String id, Date created, Date modified, String name,
                               String surname) implements Serializable {
}
