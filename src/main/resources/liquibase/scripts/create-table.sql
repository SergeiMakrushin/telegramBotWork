--liquibase formatted sql

--changeset smakrushin:1

CREATE TABLE IF NOT EXISTS public.notification_task
(
    id bigint NOT NULL DEFAULT nextval('notification_task_id_seq'::regclass),
    message character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    time_message timestamp(6) without time zone,
    user_id bigint,
    CONSTRAINT notification_task_pkey PRIMARY KEY (id)
);


