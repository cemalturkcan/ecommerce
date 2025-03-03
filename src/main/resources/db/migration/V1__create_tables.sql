CREATE TABLE IF NOT EXISTS usr
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    created  timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    email    VARCHAR(255)                            NOT NULL UNIQUE,
    password VARCHAR(255),
    role     VARCHAR(255)                            NOT NULL DEFAULT 'USER' CHECK (role IN ('ADMIN', 'USER')),
    status   boolean                                 NOT NULL DEFAULT TRUE
);

INSERT INTO usr (email, password, role)
VALUES ('admin@mail.com', '$2a$10$.PFCwHn9rAckYkSRHew/v.kAnxhPa6FEBOU1TfabBgApyJ3DD9zmO', 'ADMIN');


CREATE TABLE IF NOT EXISTS customer
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    created  timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name     VARCHAR(255)                            NOT NULL,
    surname  VARCHAR(255)                            NOT NULL,
    user_id  BIGINT                                  NOT NULL REFERENCES usr (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    created  timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name     VARCHAR(255)                            NOT NULL,
    price    DECIMAL(10, 2)                          NOT NULL,
    stock    INT                                     NOT NULL
);

CREATE TABLE IF NOT EXISTS cart
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    created     timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified    timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    customer_id BIGINT                                  NOT NULL REFERENCES customer (id) ON DELETE CASCADE,
    price       DECIMAL(10, 2)                          NOT NULL DEFAULT 0.0,
    status      VARCHAR(255)                            NOT NULL DEFAULT 'OPEN' CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

CREATE TABLE IF NOT EXISTS cart_product
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    created    timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified   timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cart_id    BIGINT                                  NOT NULL REFERENCES cart (id) ON DELETE CASCADE,
    product_id BIGINT                                  NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    quantity   INT                                     NOT NULL
);

CREATE TABLE IF NOT EXISTS ordr
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    created  timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cart_id  BIGINT                                  NOT NULL REFERENCES cart (id) ON DELETE CASCADE,
    code     VARCHAR(255)                            NOT NULL
);

CREATE TABLE IF NOT EXISTS ordr_product
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    created         timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified        timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cart_product_id BIGINT                                  NOT NULL REFERENCES cart_product (id) ON DELETE CASCADE,
    price           DECIMAL(10, 2)                          NOT NULL
);