--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-10-23 15:00:49

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 17131)
-- Name: groupviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groupviews (
    id integer NOT NULL,
    groupid integer NOT NULL,
    viewid integer NOT NULL
);


ALTER TABLE public.groupviews OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17129)
-- Name: groupviews_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.groupviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groupviews_id_seq OWNER TO postgres;

--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 197
-- Name: groupviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.groupviews_id_seq OWNED BY public.groupviews.id;


--
-- TOC entry 200 (class 1259 OID 17139)
-- Name: t_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_group (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_updation_date timestamp without time zone NOT NULL,
    description text NOT NULL,
    rating integer NOT NULL,
    title text NOT NULL
);


ALTER TABLE public.t_group OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17137)
-- Name: t_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_group_id_seq OWNER TO postgres;

--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 199
-- Name: t_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_group_id_seq OWNED BY public.t_group.id;


--
-- TOC entry 196 (class 1259 OID 16519)
-- Name: t_group_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_group_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_group_seq OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17150)
-- Name: topic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topic (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_updation_date timestamp without time zone NOT NULL,
    description text NOT NULL,
    isprivate boolean NOT NULL,
    rating integer NOT NULL,
    title text NOT NULL,
    last_read_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.topic OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17148)
-- Name: topic_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topic_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topic_id_seq OWNER TO postgres;

--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 201
-- Name: topic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topic_id_seq OWNED BY public.topic.id;


--
-- TOC entry 210 (class 1259 OID 34396)
-- Name: topic_read_history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topic_read_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.topic_read_history_id_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 34391)
-- Name: topic_read_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topic_read_history (
    id integer DEFAULT nextval('public.topic_read_history_id_seq'::regclass) NOT NULL,
    topic_id integer NOT NULL,
    last_read_date timestamp without time zone NOT NULL
);


ALTER TABLE public.topic_read_history OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17161)
-- Name: topicgroups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topicgroups (
    id integer NOT NULL,
    groupid integer NOT NULL,
    topicid integer NOT NULL
);


ALTER TABLE public.topicgroups OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17159)
-- Name: topicgroups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topicgroups_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topicgroups_id_seq OWNER TO postgres;

--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 203
-- Name: topicgroups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topicgroups_id_seq OWNED BY public.topicgroups.id;


--
-- TOC entry 206 (class 1259 OID 17169)
-- Name: view; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.view (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_updation_date timestamp without time zone NOT NULL,
    description text NOT NULL,
    rating integer NOT NULL,
    title text NOT NULL
);


ALTER TABLE public.view OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17167)
-- Name: view_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.view_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.view_id_seq OWNER TO postgres;

--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 205
-- Name: view_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.view_id_seq OWNED BY public.view.id;


--
-- TOC entry 208 (class 1259 OID 17180)
-- Name: viewtopics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.viewtopics (
    id integer NOT NULL,
    topicid integer NOT NULL,
    viewid integer NOT NULL
);


ALTER TABLE public.viewtopics OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17178)
-- Name: viewtopics_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.viewtopics_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.viewtopics_id_seq OWNER TO postgres;

--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 207
-- Name: viewtopics_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.viewtopics_id_seq OWNED BY public.viewtopics.id;


--
-- TOC entry 2711 (class 2604 OID 17134)
-- Name: groupviews id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groupviews ALTER COLUMN id SET DEFAULT nextval('public.groupviews_id_seq'::regclass);


--
-- TOC entry 2712 (class 2604 OID 17142)
-- Name: t_group id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_group ALTER COLUMN id SET DEFAULT nextval('public.t_group_id_seq'::regclass);


--
-- TOC entry 2713 (class 2604 OID 17153)
-- Name: topic id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic ALTER COLUMN id SET DEFAULT nextval('public.topic_id_seq'::regclass);


--
-- TOC entry 2715 (class 2604 OID 17164)
-- Name: topicgroups id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicgroups ALTER COLUMN id SET DEFAULT nextval('public.topicgroups_id_seq'::regclass);


--
-- TOC entry 2716 (class 2604 OID 17172)
-- Name: view id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.view ALTER COLUMN id SET DEFAULT nextval('public.view_id_seq'::regclass);


--
-- TOC entry 2717 (class 2604 OID 17183)
-- Name: viewtopics id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.viewtopics ALTER COLUMN id SET DEFAULT nextval('public.viewtopics_id_seq'::regclass);


--
-- TOC entry 2720 (class 2606 OID 17136)
-- Name: groupviews groupviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groupviews
    ADD CONSTRAINT groupviews_pkey PRIMARY KEY (id);


--
-- TOC entry 2724 (class 2606 OID 17147)
-- Name: t_group t_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_group
    ADD CONSTRAINT t_group_pkey PRIMARY KEY (id);


--
-- TOC entry 2726 (class 2606 OID 17158)
-- Name: topic topic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 34395)
-- Name: topic_read_history topic_read_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_read_history
    ADD CONSTRAINT topic_read_history_pkey PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 17166)
-- Name: topicgroups topicgroups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicgroups
    ADD CONSTRAINT topicgroups_pkey PRIMARY KEY (id);


--
-- TOC entry 2722 (class 2606 OID 17187)
-- Name: groupviews uk_8bwk7loervun51id4x7v5qua2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groupviews
    ADD CONSTRAINT uk_8bwk7loervun51id4x7v5qua2 UNIQUE (viewid, groupid);


--
-- TOC entry 2734 (class 2606 OID 17191)
-- Name: viewtopics uk_i9ni7awvi8fbvlafuj2jc3dgx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.viewtopics
    ADD CONSTRAINT uk_i9ni7awvi8fbvlafuj2jc3dgx UNIQUE (topicid, viewid);


--
-- TOC entry 2730 (class 2606 OID 17189)
-- Name: topicgroups uk_n61tvmdd3uuqk9fq6e4i16jy8; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicgroups
    ADD CONSTRAINT uk_n61tvmdd3uuqk9fq6e4i16jy8 UNIQUE (topicid, groupid);


--
-- TOC entry 2732 (class 2606 OID 17177)
-- Name: view view_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.view
    ADD CONSTRAINT view_pkey PRIMARY KEY (id);


--
-- TOC entry 2736 (class 2606 OID 17185)
-- Name: viewtopics viewtopics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.viewtopics
    ADD CONSTRAINT viewtopics_pkey PRIMARY KEY (id);


--
-- TOC entry 2740 (class 2606 OID 17197)
-- Name: groupviews fk_awjafickpxbaiu7qfntstkllu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groupviews
    ADD CONSTRAINT fk_awjafickpxbaiu7qfntstkllu FOREIGN KEY (viewid) REFERENCES public.view(id);


--
-- TOC entry 2741 (class 2606 OID 17202)
-- Name: topicgroups fk_cor9qh790ccydysg9yt8r1j1j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicgroups
    ADD CONSTRAINT fk_cor9qh790ccydysg9yt8r1j1j FOREIGN KEY (groupid) REFERENCES public.t_group(id);


--
-- TOC entry 2743 (class 2606 OID 17212)
-- Name: viewtopics fk_d537vo4b5xtguo52p1mp931c3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.viewtopics
    ADD CONSTRAINT fk_d537vo4b5xtguo52p1mp931c3 FOREIGN KEY (topicid) REFERENCES public.topic(id);


--
-- TOC entry 2742 (class 2606 OID 17207)
-- Name: topicgroups fk_fu3o7d7vrcdtyxo31l7nqvph; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicgroups
    ADD CONSTRAINT fk_fu3o7d7vrcdtyxo31l7nqvph FOREIGN KEY (topicid) REFERENCES public.topic(id);


--
-- TOC entry 2744 (class 2606 OID 17217)
-- Name: viewtopics fk_sphfrhsmk86o709nlr10wdcj1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.viewtopics
    ADD CONSTRAINT fk_sphfrhsmk86o709nlr10wdcj1 FOREIGN KEY (viewid) REFERENCES public.view(id);


--
-- TOC entry 2739 (class 2606 OID 17192)
-- Name: groupviews fk_spu9g96ug3njkoyhwwubj0kam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groupviews
    ADD CONSTRAINT fk_spu9g96ug3njkoyhwwubj0kam FOREIGN KEY (groupid) REFERENCES public.t_group(id);


-- Completed on 2018-10-23 15:00:49

--
-- PostgreSQL database dump complete
--

