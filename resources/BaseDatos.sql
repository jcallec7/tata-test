--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2024-06-28 00:17:22

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 534572)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    id bigint NOT NULL,
    numero_cuenta character varying(255),
    tipo_cuenta character varying(255),
    saldo_inicial numeric(38,2),
    estado boolean DEFAULT true NOT NULL,
    client_id bigint NOT NULL
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 534580)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    contrasenia character varying(255),
    estado boolean DEFAULT true NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 534598)
-- Name: cuentas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cuentas_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cuentas_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 534599)
-- Name: movimientos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimientos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movimientos_seq OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 534600)
-- Name: personas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.personas_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personas_seq OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 534586)
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
    id bigint NOT NULL,
    direccion character varying(255),
    edad integer,
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 534593)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    id bigint NOT NULL,
    saldo numeric(38,2),
    fecha timestamp(6) without time zone,
    tipo_movimiento character varying(255),
    valor numeric(38,2),
    cuenta_id bigint NOT NULL
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 3342 (class 0 OID 534572)
-- Dependencies: 214
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accounts (id, numero_cuenta, tipo_cuenta, saldo_inicial, estado, client_id) FROM stdin;
1	0000001	Ahorros	300.00	t	1
\.


--
-- TOC entry 3343 (class 0 OID 534580)
-- Dependencies: 215
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (contrasenia, estado, id) FROM stdin;
325632	t	2
123456	t	1
\.


--
-- TOC entry 3344 (class 0 OID 534586)
-- Dependencies: 216
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persons (id, direccion, edad, genero, identificacion, nombre, telefono) FROM stdin;
2	Cuenca	39	Femenino	0102546332	Mayra Calle	0365896521
1	Cuenca	26	Masculino	0107166555	Esteban Calle	0979376626
\.


--
-- TOC entry 3345 (class 0 OID 534593)
-- Dependencies: 217
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (id, saldo, fecha, tipo_movimiento, valor, cuenta_id) FROM stdin;
1	301.00	2024-06-27 23:32:12.760953	Credito	1.00	1
2	300.00	2024-06-27 23:32:37.958865	Debito	1.00	1
\.


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 218
-- Name: cuentas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuentas_seq', 1, true);


--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 219
-- Name: movimientos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimientos_seq', 2, true);


--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 220
-- Name: personas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.personas_seq', 2, true);


--
-- TOC entry 3190 (class 2606 OID 534608)
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);


--
-- TOC entry 3192 (class 2606 OID 534616)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 3194 (class 2606 OID 534627)
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);


--
-- TOC entry 3196 (class 2606 OID 534640)
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 534645)
-- Name: transactions fkfjjcfofw964njxgj68cqel3km; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT fkfjjcfofw964njxgj68cqel3km FOREIGN KEY (cuenta_id) REFERENCES public.accounts(id);


--
-- TOC entry 3197 (class 2606 OID 534650)
-- Name: accounts fkgymog7firrf8bnoiig61666ob; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT fkgymog7firrf8bnoiig61666ob FOREIGN KEY (client_id) REFERENCES public.clients(id);


--
-- TOC entry 3198 (class 2606 OID 534628)
-- Name: clients fko28cbg7ns1r1m76bn3sd36eps; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT fko28cbg7ns1r1m76bn3sd36eps FOREIGN KEY (id) REFERENCES public.persons(id);


-- Completed on 2024-06-28 00:17:22

--
-- PostgreSQL database dump complete
--

