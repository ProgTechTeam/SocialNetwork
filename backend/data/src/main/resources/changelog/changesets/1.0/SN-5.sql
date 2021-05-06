CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS test.test_types
(
    id   INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(128)        NOT NULL

);

INSERT INTO test.test_types
VALUES (1, 'TEST_1'),
       (2, 'TEST_2'),
       (3, 'OTHER');

CREATE TABLE IF NOT EXISTS test.tests
(
    test_id SERIAL,
    content VARCHAR(128) NOT NULL,
    type    INTEGER      NOT NULL,

    FOREIGN KEY (type) REFERENCES test.test_types (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
