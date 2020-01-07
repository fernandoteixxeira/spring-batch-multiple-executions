CREATE SEQUENCE seq_id_patient
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999;

CREATE TABLE patient (
    id integer NOT NULL DEFAULT NEXTVAL('seq_id_patient'),
    name VARCHAR(200) NOT NULL,
    CONSTRAINT patient_pkey PRIMARY KEY (id)
);

INSERT INTO patient (name) VALUES ('João');
INSERT INTO patient (name) VALUES ('Maira');
INSERT INTO patient (name) VALUES ('Ana');
INSERT INTO patient (name) VALUES ('Júlia');
INSERT INTO patient (name) VALUES ('Pedro');
INSERT INTO patient (name) VALUES ('Francisco');
INSERT INTO patient (name) VALUES ('Joaquim');
INSERT INTO patient (name) VALUES ('José');
INSERT INTO patient (name) VALUES ('Lídia');
INSERT INTO patient (name) VALUES ('Estela');
INSERT INTO patient (name) VALUES ('Gabriela');
INSERT INTO patient (name) VALUES ('Rosa');
INSERT INTO patient (name) VALUES ('Abraão');
INSERT INTO patient (name) VALUES ('Tiago');
INSERT INTO patient (name) VALUES ('Everton');
INSERT INTO patient (name) VALUES ('Mariana');
INSERT INTO patient (name) VALUES ('Jéssica');
INSERT INTO patient (name) VALUES ('Daniela');
INSERT INTO patient (name) VALUES ('Camila');
INSERT INTO patient (name) VALUES ('Carolina');
INSERT INTO patient (name) VALUES ('Fernando');
INSERT INTO patient (name) VALUES ('Jaqueline');
INSERT INTO patient (name) VALUES ('Lívia');
INSERT INTO patient (name) VALUES ('Janaina');
INSERT INTO patient (name) VALUES ('Laura');
INSERT INTO patient (name) VALUES ('Janine');
INSERT INTO patient (name) VALUES ('Marcos');
INSERT INTO patient (name) VALUES ('Paulo');