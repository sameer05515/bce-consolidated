-- Table: public.t_category

-- DROP TABLE public.t_category;

CREATE TABLE public.t_category
(
    cat_id integer NOT NULL,
    cat_name text COLLATE pg_catalog."default",
    creation_date timestamp without time zone,
    last_updation_date timestamp without time zone,
    rating integer DEFAULT 1,
    CONSTRAINT t_category_pkey PRIMARY KEY (cat_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.t_category
    OWNER to postgres;
	
	
	
-- Table: public.t_catg_ques

-- DROP TABLE public.t_catg_ques;

CREATE TABLE public.t_catg_ques
(
    ques_id integer NOT NULL,
    linked_cat_id integer NOT NULL,
    ques text COLLATE pg_catalog."default",
    creation_date timestamp without time zone,
    last_updation_date timestamp without time zone,
    rating integer DEFAULT 1,
    CONSTRAINT t_catg_ques_pkey PRIMARY KEY (ques_id, linked_cat_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.t_catg_ques
    OWNER to postgres;
	
	
	
	
-- Table: public.t_catg_ques

-- DROP TABLE public.t_catg_ques;

CREATE TABLE public.t_catg_ques_ans
(
    ans_id integer NOT NULL,
	linked_ques_id integer NOT NULL,
    linked_cat_id integer NOT NULL,
    ques text COLLATE pg_catalog."default",
    creation_date timestamp without time zone,
    last_updation_date timestamp without time zone,
    rating integer DEFAULT 1,
    CONSTRAINT t_catg_ques_ans_pkey PRIMARY KEY (ans_id,linked_ques_id, linked_cat_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.t_catg_ques_ans
    OWNER to postgres;	
	
