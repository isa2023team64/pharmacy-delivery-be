-- users
-- INSERT INTO app_user(
-- 	id, username, email, password)
-- 	VALUES (1, 'veljko', 'nikolicveljko01@gmail.com', 'super'),
-- 		   (2, 'marko', 'marko@gmail.com', 'super');

-- registered users
-- INSERT INTO registered_user(
-- 	id, first_name, last_name)
--     VALUES (1, 'Veljko', 'Nikolic'),
-- 		   (2, 'Marko', 'Nikolic');


INSERT INTO app_user (email, password, first_name, last_name, active, last_password_reset_date, city, country, phone_number, workplace, company_name)
VALUES ('user1@example.com', 'password123', 'John', 'Doe', true, CURRENT_TIMESTAMP, 'City', 'Country', '+123456789012', 'Workplace', 'Company');

INSERT INTO public.registered_user(id)
VALUES (1);