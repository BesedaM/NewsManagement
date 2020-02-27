--
-- PostgreSQL database dump
--

-- Dumped from database version 10.11
-- Dumped by pg_dump version 10.11

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

ALTER TABLE IF EXISTS ONLY news_management.news_tag DROP CONSTRAINT IF EXISTS tag_id_fk;
ALTER TABLE IF EXISTS ONLY news_management.news_tag DROP CONSTRAINT IF EXISTS news_id_fk;
ALTER TABLE IF EXISTS ONLY news_management.news_author DROP CONSTRAINT IF EXISTS news_id_fk;
ALTER TABLE IF EXISTS ONLY news_management.news_author DROP CONSTRAINT IF EXISTS author_id_fk;
DROP INDEX IF EXISTS news_management.fki_news_id_fk;
ALTER TABLE IF EXISTS ONLY news_management."user" DROP CONSTRAINT IF EXISTS user_pkey;
ALTER TABLE IF EXISTS ONLY news_management.tag DROP CONSTRAINT IF EXISTS tag_pkey;
ALTER TABLE IF EXISTS ONLY news_management.roles DROP CONSTRAINT IF EXISTS roles_pkey;
ALTER TABLE IF EXISTS ONLY news_management.news DROP CONSTRAINT IF EXISTS news_pkey;
ALTER TABLE IF EXISTS ONLY news_management.author DROP CONSTRAINT IF EXISTS author_pk;
ALTER TABLE IF EXISTS news_management.author ALTER COLUMN id DROP DEFAULT;
DROP TABLE IF EXISTS news_management."user";
DROP TABLE IF EXISTS news_management.tag;
DROP TABLE IF EXISTS news_management.roles;
DROP TABLE IF EXISTS news_management.news_tag;
DROP TABLE IF EXISTS news_management.news_author;
DROP TABLE IF EXISTS news_management.news;
DROP SEQUENCE IF EXISTS news_management.author_id_seq1;
DROP TABLE IF EXISTS news_management.author;
DROP EXTENSION IF EXISTS adminpack;
DROP EXTENSION IF EXISTS plpgsql;
DROP SCHEMA IF EXISTS public;
DROP SCHEMA IF EXISTS news_management;
--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

--COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: news_management; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA news_management;


ALTER SCHEMA news_management OWNER TO postgres;


COMMENT ON SCHEMA news_management IS 'standard public schema';

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- CREATE SCHEMA public;


-- ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

-- COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

-- CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

-- COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

-- CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

-- COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: author; Type: TABLE; Schema: news_management; Owner: postgres
--

CREATE TABLE news_management.author (
    id bigint NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL
);


ALTER TABLE news_management.author OWNER TO postgres;

--
-- Name: author_id_seq1; Type: SEQUENCE; Schema: news_management; Owner: postgres
--

CREATE SEQUENCE news_management.author_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE news_management.author_id_seq1 OWNER TO postgres;

--
-- Name: author_id_seq1; Type: SEQUENCE OWNED BY; Schema: news_management; Owner: postgres
--

ALTER SEQUENCE news_management.author_id_seq1 OWNED BY news_management.author.id;


--
-- Name: news; Type: TABLE; Schema: news_management; Owner: postgres
--

CREATE TABLE news_management.news (
    id bigint NOT NULL,
    creation_date date NOT NULL,
    modification_date date NOT NULL,
    title character varying(30) NOT NULL,
    short_text character varying(100) NOT NULL,
    full_text character varying(2000) NOT NULL
);


ALTER TABLE news_management.news OWNER TO postgres;

--
-- Name: news_author; Type: TABLE; Schema: news_management; Owner: postgres
--

CREATE TABLE news_management.news_author (
    news_id bigint NOT NULL,
    author_id bigint NOT NULL
);


ALTER TABLE news_management.news_author OWNER TO postgres;

--
-- Name: news_id_seq; Type: SEQUENCE; Schema: news_management; Owner: postgres
--

ALTER TABLE news_management.news ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME news_management.news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE
);


--
-- Name: news_tag; Type: TABLE; Schema: news_management; Owner: postgres
--

CREATE TABLE news_management.news_tag (
    news_id bigint NOT NULL,
    tag_id bigint NOT NULL
);


ALTER TABLE news_management.news_tag OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: news_management; Owner: postgres
--

CREATE TABLE news_management.roles (
    id bigint NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE news_management.roles OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: news_management; Owner: postgres
--

ALTER TABLE news_management.roles ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME news_management.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE
);


--
-- Name: tag; Type: TABLE; Schema: news_management; Owner: postgres
--

CREATE TABLE news_management.tag (
    id bigint NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE news_management.tag OWNER TO postgres;

--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: news_management; Owner: postgres
--

ALTER TABLE news_management.tag ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME news_management.tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE
);


--
-- Name: user; Type: TABLE; Schema: news_management; Owner: postgres
--

CREATE TABLE news_management."user" (
    id bigint NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(30) NOT NULL,
    role_id bigint
);


ALTER TABLE news_management."user" OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: news_management; Owner: postgres
--

ALTER TABLE news_management."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME news_management.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE
);


--
-- Name: author id; Type: DEFAULT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.author ALTER COLUMN id SET DEFAULT nextval('news_management.author_id_seq1'::regclass);

--
-- Name: author author_pk; Type: CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.author
    ADD CONSTRAINT author_pk PRIMARY KEY (id);


--
-- Name: news news_pkey; Type: CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: fki_news_id_fk; Type: INDEX; Schema: news_management; Owner: postgres
--

CREATE INDEX fki_news_id_fk ON news_management.news_author USING btree (news_id);


--
-- Name: news_author author_id_fk; Type: FK CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.news_author
    ADD CONSTRAINT author_id_fk FOREIGN KEY (author_id) REFERENCES news_management.author(id) NOT VALID;


--
-- Name: news_author news_id_fk; Type: FK CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.news_author
    ADD CONSTRAINT news_id_fk FOREIGN KEY (news_id) REFERENCES news_management.news(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: news_tag news_id_fk; Type: FK CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.news_tag
    ADD CONSTRAINT news_id_fk FOREIGN KEY (news_id) REFERENCES news_management.news(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: news_tag tag_id_fk; Type: FK CONSTRAINT; Schema: news_management; Owner: postgres
--

ALTER TABLE ONLY news_management.news_tag
    ADD CONSTRAINT tag_id_fk FOREIGN KEY (tag_id) REFERENCES news_management.tag(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

