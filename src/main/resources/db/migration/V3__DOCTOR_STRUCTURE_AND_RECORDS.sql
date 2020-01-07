CREATE SEQUENCE seq_id_doctor
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999;

CREATE TABLE doctor (
    id integer NOT NULL DEFAULT NEXTVAL('seq_id_doctor'),
    name VARCHAR(200) NOT NULL,
    CONSTRAINT doctor_pkey PRIMARY KEY (id)
);

INSERT INTO doctor (name) VALUES ('João');
INSERT INTO doctor (name) VALUES ('Maira');
INSERT INTO doctor (name) VALUES ('Ana');
INSERT INTO doctor (name) VALUES ('Júlia');
INSERT INTO doctor (name) VALUES ('Pedro');
INSERT INTO doctor (name) VALUES ('Francisco');
INSERT INTO doctor (name) VALUES ('Joaquim');
INSERT INTO doctor (name) VALUES ('José');
INSERT INTO doctor (name) VALUES ('Lídia');
INSERT INTO doctor (name) VALUES ('Estela');
INSERT INTO doctor (name) VALUES ('Gabriela');
INSERT INTO doctor (name) VALUES ('Rosa');
INSERT INTO doctor (name) VALUES ('Abraão');
INSERT INTO doctor (name) VALUES ('Tiago');
INSERT INTO doctor (name) VALUES ('Everton');
INSERT INTO doctor (name) VALUES ('Mariana');
INSERT INTO doctor (name) VALUES ('Jéssica');
INSERT INTO doctor (name) VALUES ('Daniela');
INSERT INTO doctor (name) VALUES ('Camila');
INSERT INTO doctor (name) VALUES ('Carolina');
INSERT INTO doctor (name) VALUES ('Fernando');
INSERT INTO doctor (name) VALUES ('Jaqueline');
INSERT INTO doctor (name) VALUES ('Lívia');
INSERT INTO doctor (name) VALUES ('Janaina');
INSERT INTO doctor (name) VALUES ('Laura');
INSERT INTO doctor (name) VALUES ('Janine');
INSERT INTO doctor (name) VALUES ('Marcos');
INSERT INTO doctor (name) VALUES ('Paulo');