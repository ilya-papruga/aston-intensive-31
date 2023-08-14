SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


CREATE DATABASE university WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE university OWNER TO postgres;

\connect university

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


CREATE SCHEMA main;


ALTER SCHEMA main OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE main.courses (
    id bigint NOT NULL,
    title character varying
);


ALTER TABLE main.courses OWNER TO postgres;


CREATE SEQUENCE main.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.courses_id_seq OWNER TO postgres;


ALTER SEQUENCE main.courses_id_seq OWNED BY main.courses.id;

CREATE TABLE main.cross_courses_students (
    course_id bigint NOT NULL,
    student_id bigint NOT NULL
);


ALTER TABLE main.cross_courses_students OWNER TO postgres;

CREATE TABLE main.cross_groups_students (
    group_id bigint,
    student_id bigint
);


ALTER TABLE main.cross_groups_students OWNER TO postgres;


CREATE TABLE main.groups (
    id bigint NOT NULL,
    number character varying
);


ALTER TABLE main.groups OWNER TO postgres;


CREATE SEQUENCE main.group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.group_id_seq OWNER TO postgres;


ALTER SEQUENCE main.group_id_seq OWNED BY main.groups.id;


CREATE TABLE main.students (
    id bigint NOT NULL,
    name character varying,
    age bigint,
    score double precision,
    olympic_gamer boolean
);


ALTER TABLE main.students OWNER TO postgres;


CREATE SEQUENCE main.students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.students_id_seq OWNER TO postgres;


ALTER SEQUENCE main.students_id_seq OWNED BY main.students.id;


ALTER TABLE ONLY main.courses ALTER COLUMN id SET DEFAULT nextval('main.courses_id_seq'::regclass);


ALTER TABLE ONLY main.groups ALTER COLUMN id SET DEFAULT nextval('main.group_id_seq'::regclass);


ALTER TABLE ONLY main.students ALTER COLUMN id SET DEFAULT nextval('main.students_id_seq'::regclass);


ALTER TABLE ONLY main.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


ALTER TABLE ONLY main.groups
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


ALTER TABLE ONLY main.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


ALTER TABLE ONLY main.cross_groups_students
    ADD CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES main.groups(id) NOT VALID;


ALTER TABLE ONLY main.cross_groups_students
    ADD CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES main.students(id) NOT VALID;