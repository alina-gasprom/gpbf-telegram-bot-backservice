DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq AS BIGINT START WITH 1000;

create TABLE users
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    telegram_id BIGINT UNIQUE      NOT NULL,
    user_name   VARCHAR(50) UNIQUE NOT NULL
);