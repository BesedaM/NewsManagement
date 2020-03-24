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

--
-- Data for Name: author; Type: TABLE DATA; Schema: news_management; Owner: postgres
--

INSERT INTO news_management.author (id, name, surname) OVERRIDING SYSTEM VALUE VALUES (1, 'Serhey', 'Chizikov');
INSERT INTO news_management.author (id, name, surname) OVERRIDING SYSTEM VALUE VALUES (2, 'Alina', 'Bodoeva');
INSERT INTO news_management.author (id, name, surname) OVERRIDING SYSTEM VALUE VALUES (3, 'Dzenis', 'Matsuev');
INSERT INTO news_management.author (id, name, surname) OVERRIDING SYSTEM VALUE VALUES (4, 'Oksana', 'Piharchuk');
INSERT INTO news_management.author (id, name, surname) OVERRIDING SYSTEM VALUE VALUES (5, 'Kiril', 'Koshka');
INSERT INTO news_management.author (id, name, surname) OVERRIDING SYSTEM VALUE VALUES (6, 'Inga', 'Mazheika');
INSERT INTO news_management.author (id, name, surname) OVERRIDING SYSTEM VALUE VALUES (7, 'Kanstantin', 'Yulov');


--
-- Data for Name: news; Type: TABLE DATA; Schema: news_management; Owner: postgres
--

INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (1, '2020-02-10', '2020-02-10', 'weather_10_02_2020', 'the weather is nice', 'the weather is nice');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (3, '2020-02-10', '2020-02-10', 'wether_12_02_2020', 'the weather is more than just nice', 'the weather is more than just nice');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (4, '2020-02-10', '2020-02-10', 'weather_13_02_2020', 'it is awesome!', 'it is awesome!');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (7, '2020-02-10', '2020-02-10', 'tommy_story_01', 'Tommy moved...', 'Tommy moved...');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (8, '2020-02-10', '2020-02-10', 'tommy_story_03', 'Tommy visit Mr. Jenkins....', 'Tommy visit Mr. Jenkins....');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (9, '2020-02-10', '2020-02-10', 'tommy_story_03', 'Tommy met Frey', 'Tommy met Frey');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (10, '2020-02-10', '2020-02-10', 'granny_advice_10_02_2020', 'Sleep well', 'Sleep well');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (11, '2020-02-10', '2020-02-10', 'granny_advice_11_02_2020', 'Eat good', 'Eat good');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (12, '2020-02-10', '2020-02-10', 'granny_advice_12_02_2020', 'Have good friends', 'Have good friends');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (2, '2020-02-10', '2020-02-10', 'weather_11_02_2020', 'the weather is very nice', 'the weather is very nice');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (13, '2020-02-10', '2020-02-10', 'new_in_word_10_02_2020', 'China is closed...', 'China is closed...');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (14, '2020-02-10', '2020-02-10', 'new_in_word_11_02_2020', 'Aliexpress stops money transactions', 'Aliexpress stops money transactions');
INSERT INTO news_management.news (id, creation_date, modification_date, title, short_text, full_text) OVERRIDING SYSTEM VALUE VALUES (15, '2020-02-10', '2020-02-10', 'new_in_word_12_02_2020', 'Japan is under danger of epidemy', 'Japan is under danger of epidemy');


--
-- Data for Name: news_author; Type: TABLE DATA; Schema: news_management; Owner: postgres
--

INSERT INTO news_management.news_author (news_id, author_id) VALUES (1, 2);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (2, 2);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (3, 2);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (4, 2);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (10, 4);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (11, 4);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (12, 4);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (7, 3);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (9, 3);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (8, 3);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (13, 1);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (14, 1);
INSERT INTO news_management.news_author (news_id, author_id) VALUES (15, 1);


--
-- Data for Name: tag; Type: TABLE DATA; Schema: news_management; Owner: postgres
--

INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (1, 'advice');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (2, 'granny');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (3, 'wisdom');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (4, 'weather');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (5, 'fiction');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (6, 'tommy');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (7, 'forecast');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (8, 'story');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (9, 'China');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (10, 'epidemic');
INSERT INTO news_management.tag (id, name)  OVERRIDING SYSTEM VALUE VALUES (11, 'news');


--
-- Data for Name: news_tag; Type: TABLE DATA; Schema: news_management; Owner: postgres
--

INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (1, 4);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (1, 7);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (2, 4);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (2, 7);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (3, 4);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (3, 7);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (4, 4);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (4, 7);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (7, 5);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (7, 6);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (7, 8);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (8, 5);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (8, 6);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (8, 8);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (9, 5);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (9, 6);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (9, 8);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (10, 1);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (10, 2);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (10, 3);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (11, 1);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (11, 2);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (11, 3);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (12, 1);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (12, 2);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (12, 3);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (13, 9);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (13, 10);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (13, 11);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (14, 9);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (14, 10);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (14, 11);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (15, 9);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (15, 10);
INSERT INTO news_management.news_tag (news_id, tag_id) VALUES (15, 11);


--
-- Data for Name: roles; Type: TABLE DATA; Schema: news_management; Owner: postgres
--

INSERT INTO news_management.roles (id, name)  OVERRIDING SYSTEM VALUE VALUES (2, 'admin');
INSERT INTO news_management.roles (id, name)  OVERRIDING SYSTEM VALUE VALUES (3, 'author');
INSERT INTO news_management.roles (id, name)  OVERRIDING SYSTEM VALUE VALUES (4, 'guest');


--
-- Data for Name: user; Type: TABLE DATA; Schema: news_management; Owner: postgres
--

INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (1, 'admin', 'admin', 'admin', 'admin', 2);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (2, 'Serhey', 'Chizikov', 'chiz', '12345', 3);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (3, 'Alina', 'Bodoeva', 'bodoeva_a', '12389', 3);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (4, 'Dzenis', 'Matsuev', 'matsuev_d', '77643', 3);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (5, 'Oksana', 'Piharchuk', 'piharchuk_o', '456774', 3);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (6, 'Kiril', 'Koshka', 'koshka_k', '684_k9', 3);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (7, 'Inga', 'Mazheika', 'mazheika_i', '7589_oo', 3);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (8, 'Kanstantin', 'Yulov', 'yulov_k', '453_pi', 3);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (9, 'Lubov', 'Mazurova', 'maz_lub', 'qwerty333', 4);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (10, 'Tatiana', 'Suslo', 'suslik', 'yurii1998', 4);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (11, 'Svietlana', 'Tropenka', 'rukka', '346uuu', 4);
INSERT INTO news_management."user" (id, name, surname, login, password, role_id)  OVERRIDING SYSTEM VALUE VALUES (12, 'Fiodar', 'Hutarskoy', 'golovach', 'zimmal', 4);


--
-- Name: author_id_seq1; Type: SEQUENCE SET; Schema: news_management; Owner: postgres
--

SELECT pg_catalog.setval('news_management.author_id_seq', 7, true);


--
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: news_management; Owner: postgres
--

SELECT pg_catalog.setval('news_management.news_id_seq', 15, true);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: news_management; Owner: postgres
--

SELECT pg_catalog.setval('news_management.roles_id_seq', 4, true);


--
-- Name: tag_id_seq; Type: SEQUENCE SET; Schema: news_management; Owner: postgres
--

SELECT pg_catalog.setval('news_management.tag_id_seq', 11, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: news_management; Owner: postgres
--

SELECT pg_catalog.setval('news_management.user_id_seq', 12, true);


--
-- PostgreSQL database dump complete
--

