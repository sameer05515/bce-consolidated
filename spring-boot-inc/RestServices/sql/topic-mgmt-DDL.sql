CREATE SEQUENCE public.groupviews_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.groupviews_id_seq
  OWNER TO postgres;
  
  
CREATE SEQUENCE public.t_group_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.t_group_id_seq
  OWNER TO postgres;

  
CREATE SEQUENCE public.topic_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.topic_id_seq
  OWNER TO postgres;

  
CREATE SEQUENCE public.topicgroups_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.topicgroups_id_seq
  OWNER TO postgres;
  
  
CREATE SEQUENCE public.view_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.view_id_seq
  OWNER TO postgres;

  
CREATE SEQUENCE public.viewtopics_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.viewtopics_id_seq
  OWNER TO postgres;



  
CREATE TABLE public.t_group
(
  id integer NOT NULL DEFAULT nextval('t_group_id_seq'::regclass),
  creation_date timestamp without time zone NOT NULL,
  last_updation_date timestamp without time zone NOT NULL,
  description text NOT NULL,
  title text NOT NULL,
  CONSTRAINT t_group_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.t_group
  OWNER TO postgres;

  
CREATE TABLE public.topic
(
  id integer NOT NULL DEFAULT nextval('topic_id_seq'::regclass),
  creation_date timestamp without time zone NOT NULL,
  last_updation_date timestamp without time zone NOT NULL,
  description text NOT NULL,
  isprivate boolean NOT NULL,
  title text NOT NULL,
  CONSTRAINT topic_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.topic
  OWNER TO postgres;

  


  
CREATE TABLE public.view
(
  id integer NOT NULL DEFAULT nextval('view_id_seq'::regclass),
  creation_date timestamp without time zone NOT NULL,
  last_updation_date timestamp without time zone NOT NULL,
  description text NOT NULL,
  title text NOT NULL,
  CONSTRAINT view_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.view
  OWNER TO postgres;

  
CREATE TABLE public.viewtopics
(
  id integer NOT NULL DEFAULT nextval('viewtopics_id_seq'::regclass),
  topicid integer NOT NULL,
  viewid integer NOT NULL,
  CONSTRAINT viewtopics_pkey PRIMARY KEY (id),
  CONSTRAINT fk_d537vo4b5xtguo52p1mp931c3 FOREIGN KEY (topicid)
      REFERENCES public.topic (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sphfrhsmk86o709nlr10wdcj1 FOREIGN KEY (viewid)
      REFERENCES public.view (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_i9ni7awvi8fbvlafuj2jc3dgx UNIQUE (topicid, viewid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.viewtopics
  OWNER TO postgres;
  
  
CREATE TABLE public.topicgroups
(
  id integer NOT NULL DEFAULT nextval('topicgroups_id_seq'::regclass),
  groupid integer NOT NULL,
  topicid integer NOT NULL,
  CONSTRAINT topicgroups_pkey PRIMARY KEY (id),
  CONSTRAINT fk_cor9qh790ccydysg9yt8r1j1j FOREIGN KEY (groupid)
      REFERENCES public.t_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_fu3o7d7vrcdtyxo31l7nqvph FOREIGN KEY (topicid)
      REFERENCES public.topic (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_n61tvmdd3uuqk9fq6e4i16jy8 UNIQUE (topicid, groupid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.topicgroups
  OWNER TO postgres;


CREATE TABLE public.groupviews
(
  id integer NOT NULL DEFAULT nextval('groupviews_id_seq'::regclass),
  groupid integer NOT NULL,
  viewid integer NOT NULL,
  CONSTRAINT groupviews_pkey PRIMARY KEY (id),
  CONSTRAINT fk_awjafickpxbaiu7qfntstkllu FOREIGN KEY (viewid)
      REFERENCES public.view (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_spu9g96ug3njkoyhwwubj0kam FOREIGN KEY (groupid)
      REFERENCES public.t_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_8bwk7loervun51id4x7v5qua2 UNIQUE (viewid, groupid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.groupviews
  OWNER TO postgres;
