--liquibase formatted sql

--changeset admin:initial_drop
DROP TABLE IF EXISTS incubation_data;

--changeset admin:incubation_data
CREATE TABLE incubation_data (
  id SERIAL,
  day_number INTEGER,
  date TIMESTAMP WITHOUT TIME ZONE NOT NULL ,
  point VARCHAR(50) NOT NULL ,
  value VARCHAR(20) NOT NULL,
  PRIMARY KEY(id)
);
CREATE INDEX ON incubation_data(date);
CREATE INDEX ON incubation_data(point);




