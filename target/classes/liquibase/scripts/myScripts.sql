-- liquibase formatted sql

-- changeset mcharliev:1

CREATE TABLE detail_task
(
    id           SERIAL NOT NULL PRIMARY KEY,
    name         TEXT,
    chat_id      BIGINT,
    phone_Number TEXT
);

CREATE TABLE owners_reports
(
    id                  SERIAL NOT NULL PRIMARY KEY,
    chat_id             BIGINT,
    name                TEXT,
    string_report       TEXT,
    photo_report        BYTEA,
    last_report         timestamp,
    start_probation     timestamp,
    end_probation       timestamp,
    probationary_status TEXT,
    period_extend       INTEGER
);
CREATE TABLE dogs
(
    id         SERIAL NOT NULL PRIMARY KEY,
    birth_date DATE,
    name       TEXT
);
