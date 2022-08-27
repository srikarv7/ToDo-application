--
-- rootQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

-- Started on 2022-08-14 18:11:39

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

--
-- TOC entry 5 (class 2615 OID 16570)
-- Name: todo; Type: SCHEMA; Schema: -; Owner: root
--

CREATE SCHEMA IF NOT EXISTS todo;


ALTER SCHEMA todo OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 243 (class 1259 OID 25229)
-- Name: appuser; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.appuser (
    id bigint NOT NULL,
    email character varying(255),
    enabled boolean,
    firstname character varying(255),
    lastname character varying(255),
    locked boolean,
    password character varying(255),
    userrole character varying(255)
);


ALTER TABLE todo.appuser OWNER TO root;

--
-- TOC entry 242 (class 1259 OID 25228)
-- Name: appuser_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.appuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.appuser_id_seq OWNER TO root;

--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 242
-- Name: appuser_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.appuser_id_seq OWNED BY todo.appuser.id;


--
-- TOC entry 245 (class 1259 OID 25238)
-- Name: attachment; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.attachment (
    attachment_id bigint NOT NULL,
    attachmentname character varying(255),
    created_at timestamp without time zone,
    taskid bigint NOT NULL
);


ALTER TABLE todo.attachment OWNER TO root;

--
-- TOC entry 244 (class 1259 OID 25237)
-- Name: attachment_attachment_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.attachment_attachment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.attachment_attachment_id_seq OWNER TO root;

--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 244
-- Name: attachment_attachment_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.attachment_attachment_id_seq OWNED BY todo.attachment.attachment_id;


--
-- TOC entry 247 (class 1259 OID 25245)
-- Name: comment; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.comment (
    comment_id bigint NOT NULL,
    comment character varying(255),
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    taskid bigint NOT NULL
);


ALTER TABLE todo.comment OWNER TO root;

--
-- TOC entry 246 (class 1259 OID 25244)
-- Name: comment_comment_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.comment_comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.comment_comment_id_seq OWNER TO root;

--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 246
-- Name: comment_comment_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.comment_comment_id_seq OWNED BY todo.comment.comment_id;


--
-- TOC entry 241 (class 1259 OID 25227)
-- Name: confirmation_token_sequence; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.confirmation_token_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.confirmation_token_sequence OWNER TO root;

--
-- TOC entry 248 (class 1259 OID 25251)
-- Name: confirmationtoken; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.confirmationtoken (
    id bigint NOT NULL,
    confirmedat timestamp without time zone,
    createdat timestamp without time zone NOT NULL,
    expiresat timestamp without time zone NOT NULL,
    token character varying(255) NOT NULL,
    app_user_id bigint NOT NULL
);


ALTER TABLE todo.confirmationtoken OWNER TO root;

--
-- TOC entry 250 (class 1259 OID 25257)
-- Name: reminder; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.reminder (
    reminder_id bigint NOT NULL,
    created_at timestamp without time zone,
    remindertime timestamp without time zone,
    updated_at timestamp without time zone,
    taskid bigint NOT NULL
);


ALTER TABLE todo.reminder OWNER TO root;

--
-- TOC entry 249 (class 1259 OID 25256)
-- Name: reminder_reminder_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.reminder_reminder_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.reminder_reminder_id_seq OWNER TO root;

--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 249
-- Name: reminder_reminder_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.reminder_reminder_id_seq OWNED BY todo.reminder.reminder_id;


--
-- TOC entry 252 (class 1259 OID 25264)
-- Name: tag; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.tag (
    tag_id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255),
    userid bigint NOT NULL
);


ALTER TABLE todo.tag OWNER TO root;

--
-- TOC entry 251 (class 1259 OID 25263)
-- Name: tag_tag_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.tag_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.tag_tag_id_seq OWNER TO root;

--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 251
-- Name: tag_tag_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.tag_tag_id_seq OWNED BY todo.tag.tag_id;


--
-- TOC entry 254 (class 1259 OID 25271)
-- Name: task; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.task (
    task_id bigint NOT NULL,
    created_at timestamp without time zone,
    duedate timestamp without time zone,
    priority integer,
    state character varying(255),
    summary character varying(255),
    taskname character varying(255),
    updated_at timestamp without time zone,
    listid bigint NOT NULL
);


ALTER TABLE todo.task OWNER TO root;

--
-- TOC entry 253 (class 1259 OID 25270)
-- Name: task_task_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.task_task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.task_task_id_seq OWNER TO root;

--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 253
-- Name: task_task_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.task_task_id_seq OWNED BY todo.task.task_id;


--
-- TOC entry 256 (class 1259 OID 25280)
-- Name: todolist; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.todolist (
    list_id bigint NOT NULL,
    created_at timestamp without time zone,
    listname character varying(255),
    updated_at timestamp without time zone,
    userid bigint NOT NULL
);


ALTER TABLE todo.todolist OWNER TO root;

--
-- TOC entry 255 (class 1259 OID 25279)
-- Name: todolist_list_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.todolist_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.todolist_list_id_seq OWNER TO root;

--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 255
-- Name: todolist_list_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.todolist_list_id_seq OWNED BY todo.todolist.list_id;


--
-- TOC entry 258 (class 1259 OID 25287)
-- Name: usertable; Type: TABLE; Schema: todo; Owner: root
--

CREATE TABLE todo.usertable (
    user_id bigint NOT NULL,
    email character varying(255),
    firstname character varying(255),
    is_validated boolean,
    lastname character varying(255),
    middlename character varying(255)
);


ALTER TABLE todo.usertable OWNER TO root;

--
-- TOC entry 257 (class 1259 OID 25286)
-- Name: usertable_user_id_seq; Type: SEQUENCE; Schema: todo; Owner: root
--

CREATE SEQUENCE todo.usertable_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo.usertable_user_id_seq OWNER TO root;

--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 257
-- Name: usertable_user_id_seq; Type: SEQUENCE OWNED BY; Schema: todo; Owner: root
--

ALTER SEQUENCE todo.usertable_user_id_seq OWNED BY todo.usertable.user_id;


--
-- TOC entry 3272 (class 2604 OID 25232)
-- Name: appuser id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.appuser ALTER COLUMN id SET DEFAULT nextval('todo.appuser_id_seq'::regclass);


--
-- TOC entry 3273 (class 2604 OID 25241)
-- Name: attachment attachment_id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.attachment ALTER COLUMN attachment_id SET DEFAULT nextval('todo.attachment_attachment_id_seq'::regclass);


--
-- TOC entry 3274 (class 2604 OID 25248)
-- Name: comment comment_id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.comment ALTER COLUMN comment_id SET DEFAULT nextval('todo.comment_comment_id_seq'::regclass);


--
-- TOC entry 3275 (class 2604 OID 25260)
-- Name: reminder reminder_id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.reminder ALTER COLUMN reminder_id SET DEFAULT nextval('todo.reminder_reminder_id_seq'::regclass);


--
-- TOC entry 3276 (class 2604 OID 25267)
-- Name: tag tag_id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.tag ALTER COLUMN tag_id SET DEFAULT nextval('todo.tag_tag_id_seq'::regclass);


--
-- TOC entry 3277 (class 2604 OID 25274)
-- Name: task task_id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.task ALTER COLUMN task_id SET DEFAULT nextval('todo.task_task_id_seq'::regclass);


--
-- TOC entry 3278 (class 2604 OID 25283)
-- Name: todolist list_id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.todolist ALTER COLUMN list_id SET DEFAULT nextval('todo.todolist_list_id_seq'::regclass);


--
-- TOC entry 3279 (class 2604 OID 25290)
-- Name: usertable user_id; Type: DEFAULT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.usertable ALTER COLUMN user_id SET DEFAULT nextval('todo.usertable_user_id_seq'::regclass);


--
-- TOC entry 3281 (class 2606 OID 25236)
-- Name: appuser appuser_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);


--
-- TOC entry 3283 (class 2606 OID 25243)
-- Name: attachment attachment_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.attachment
    ADD CONSTRAINT attachment_pkey PRIMARY KEY (attachment_id);


--
-- TOC entry 3285 (class 2606 OID 25250)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (comment_id);


--
-- TOC entry 3287 (class 2606 OID 25255)
-- Name: confirmationtoken confirmationtoken_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.confirmationtoken
    ADD CONSTRAINT confirmationtoken_pkey PRIMARY KEY (id);


--
-- TOC entry 3289 (class 2606 OID 25262)
-- Name: reminder reminder_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.reminder
    ADD CONSTRAINT reminder_pkey PRIMARY KEY (reminder_id);


--
-- TOC entry 3291 (class 2606 OID 25269)
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_id);


--
-- TOC entry 3293 (class 2606 OID 25278)
-- Name: task task_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (task_id);


--
-- TOC entry 3295 (class 2606 OID 25285)
-- Name: todolist todolist_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.todolist
    ADD CONSTRAINT todolist_pkey PRIMARY KEY (list_id);


--
-- TOC entry 3297 (class 2606 OID 25294)
-- Name: usertable usertable_pkey; Type: CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.usertable
    ADD CONSTRAINT usertable_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3304 (class 2606 OID 25325)
-- Name: todolist fk399q0auq2crwso2wri98fv6p; Type: FK CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.todolist
    ADD CONSTRAINT fk399q0auq2crwso2wri98fv6p FOREIGN KEY (userid) REFERENCES todo.usertable(user_id);


--
-- TOC entry 3301 (class 2606 OID 25310)
-- Name: reminder fk6pxq5q73i0p4q5vn7nfb3ej6i; Type: FK CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.reminder
    ADD CONSTRAINT fk6pxq5q73i0p4q5vn7nfb3ej6i FOREIGN KEY (taskid) REFERENCES todo.task(task_id);


--
-- TOC entry 3299 (class 2606 OID 25300)
-- Name: comment fkaoyeg6jwocxhk5995agx30hc7; Type: FK CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.comment
    ADD CONSTRAINT fkaoyeg6jwocxhk5995agx30hc7 FOREIGN KEY (taskid) REFERENCES todo.task(task_id);


--
-- TOC entry 3298 (class 2606 OID 25295)
-- Name: attachment fkgy0htxtg6f0ugo3blm38tul04; Type: FK CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.attachment
    ADD CONSTRAINT fkgy0htxtg6f0ugo3blm38tul04 FOREIGN KEY (taskid) REFERENCES todo.task(task_id);


--
-- TOC entry 3302 (class 2606 OID 25315)
-- Name: tag fkhx80gydmucik61s9h7pwo6k9c; Type: FK CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.tag
    ADD CONSTRAINT fkhx80gydmucik61s9h7pwo6k9c FOREIGN KEY (userid) REFERENCES todo.usertable(user_id);


--
-- TOC entry 3300 (class 2606 OID 25305)
-- Name: confirmationtoken fkqqt6vpst7sihcjvom2j3aqqee; Type: FK CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.confirmationtoken
    ADD CONSTRAINT fkqqt6vpst7sihcjvom2j3aqqee FOREIGN KEY (app_user_id) REFERENCES todo.appuser(id);


--
-- TOC entry 3303 (class 2606 OID 25320)
-- Name: task fkr67swvo2pg1qw4blobotjm9hr; Type: FK CONSTRAINT; Schema: todo; Owner: root
--

ALTER TABLE ONLY todo.task
    ADD CONSTRAINT fkr67swvo2pg1qw4blobotjm9hr FOREIGN KEY (listid) REFERENCES todo.todolist(list_id);


-- Completed on 2022-08-14 18:11:39

--
-- rootQL database dump complete
--