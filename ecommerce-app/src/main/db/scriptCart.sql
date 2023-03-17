DROP TABLE IF EXISTS  product;

CREATE TABLE product (
  id INT NOT NULL,
  description VARCHAR(255) NOT NULL,
  stock INT PRECISION NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS  cart;

CREATE TABLE cart (
  id INT NOT NULL AUTO_INCREMENT,
  creation_date DATETIME NOT NULL,
  last_activity DATETIME NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS  cart_product;

CREATE TABLE cart_product (
  cart_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (cart_id, product_id),
  FOREIGN KEY (cart_id) REFERENCES cart(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);