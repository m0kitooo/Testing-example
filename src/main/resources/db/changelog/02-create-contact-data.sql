CREATE SEQUENCE email_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE phone_number_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE address_id_seq START 1 INCREMENT 1;

CREATE TABLE emails (
  id        BIGINT DEFAULT nextval('email_id_seq') PRIMARY KEY,
  person_id BIGINT REFERENCES person(id) NOT NULL,
  email     VARCHAR(255) NOT NULL
);

CREATE TABLE phone_numbers (
  id           BIGINT DEFAULT nextval('phone_number_id_seq') PRIMARY KEY,
  person_id    BIGINT REFERENCES person(id) NOT NULL,
  phone_number VARCHAR(50) NOT NULL
);

CREATE TABLE addresses (
  id        BIGINT DEFAULT nextval('address_id_seq') PRIMARY KEY,
  person_id BIGINT REFERENCES person(id) NOT NULL,
  address   VARCHAR(255) NOT NULL
);
