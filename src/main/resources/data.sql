INSERT INTO public.company(
	average_rating, closing_time, opening_time, address, city, country, description, name)
	VALUES (0, '08:00:00', '20:00:00', 'Bulevar Oslobodjenja', 'Novi Sad', 'Serbia', 'Medical equipment', 'Hemofarm');

INSERT INTO app_user (email, password, first_name, last_name, active, last_password_reset_date, city, country, phone_number, workplace, company_name)
VALUES ('user1@example.com', 'password123', 'John', 'Doe', true, CURRENT_TIMESTAMP, 'City', 'Country', '+123456789012', 'Workplace', 'Company'),
       ('veljko@example.com', 'super', 'Veljko', 'Nikolic', true, CURRENT_TIMESTAMP, 'Novi Sad', 'Serbia', '+123456789012', 'Doctor', 'Poliklinika'),
       ('nikolic@example.com', 'sifra', 'Veljko', 'Nikolic', true, CURRENT_TIMESTAMP, 'Novi Sad', 'Serbia', '+381603080177', 'Administrator', 'Hemofarm');

INSERT INTO public.registered_user(id)
VALUES (1),
       (2);

INSERT INTO public.company_administrator(
	company_id, id)
	VALUES (1, 3);

INSERT INTO public.equipment(
	name, description)
	VALUES ('Injekcija', 'Za vakcinisanje'),
		   ('Stetoskop', 'Za slusanje srca');