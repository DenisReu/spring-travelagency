INSERT INTO public.country (id, name) VALUES (13, 'Italy');
INSERT INTO public.country (id, name) VALUES (16, 'Russia');
INSERT INTO public.country (id, name) VALUES (17, 'Spain');


INSERT INTO public.hotel (hotel_name, phone, stars, hotel_id, country_id) VALUES ('Salateira', '+742422', 13, 6, 13);
INSERT INTO public.hotel (hotel_name, phone, stars, hotel_id, country_id) VALUES ('Santorini', '+242342', 16, 7, 16);
INSERT INTO public.hotel (hotel_name, phone, stars, hotel_id, country_id) VALUES ('Europe', '+4234242', 17, 9, 17);


INSERT INTO public.tour (description, cost, tour_id, country_id, hotel_id, type, photo, date, duration)
VALUES ('well', 122, 2, 16, 6, 1, 'jpeg', '2018-05-11 00:00:00.000000', 7);
INSERT INTO public.tour (description, cost, tour_id, country_id, hotel_id, type, photo, date, duration)
VALUES ('good', 99, 5, 13, 7, 1, 'jpeg', '2018-05-11 00:00:00.000000', 5);
INSERT INTO public.tour (description, cost, tour_id, country_id, hotel_id, type, photo, date, duration)
VALUES ('so-so', 299, 4, 17, 9, 2, 'jpeg', '2018-05-11 00:00:00.000000', 4);


INSERT INTO public.a_user (user_id, login, password) VALUES (5, 'Roma', '4342');
INSERT INTO public.a_user (user_id, login, password) VALUES (6, 'ARTUR', '3322');
INSERT INTO public.a_user (user_id, login, password) VALUES (7, 'SEMEN', '2121');

INSERT INTO public.review (content, review_id, tour_id, user_id) VALUES ('bad', 2, 2, 5);
INSERT INTO public.review (content, review_id, tour_id, user_id) VALUES ('well', 3, 5, 6);
INSERT INTO public.review (content, review_id, tour_id, user_id) VALUES ('good', 4, 4, 7);


INSERT INTO public.a_order (order_id, user_id, tour_id, amt) VALUES (1, 5, 2, 1);
INSERT INTO public.a_order (order_id, user_id, tour_id, amt) VALUES (2, 6, 4, 1);
INSERT INTO public.a_order (order_id, user_id, tour_id, amt) VALUES (3, 7, 5, 3);

