CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255),
    category VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    weight VARCHAR(255) NOT NULL,
    rating DOUBLE,
    warranty VARCHAR(255),
    available_units INT NOT NULL,
    features VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS image (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    image_data CLOB NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);