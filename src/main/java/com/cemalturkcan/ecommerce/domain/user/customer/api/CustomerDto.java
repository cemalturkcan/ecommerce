package com.cemalturkcan.ecommerce.domain.user.customer.api;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto implements Serializable {
    private Long id;
    private Date created;
    private Date modified;
    private String name;
    private String surname;
}
