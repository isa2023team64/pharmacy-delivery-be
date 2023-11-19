INSERT INTO public.company(
	average_rating, closing_time, opening_time, address, city, country, description, name)
	VALUES (0, '08:00:00', '20:00:00', 'Bulevar Oslobodjenja', 'Novi Sad', 'Serbia', 'Medical equipment', 'Hemofarm'),
			(4, '09:00:00', '21:00:00', 'Bulevar Cara Lazara', 'Novi Sad', 'Serbia', 'New medical equipment', 'MediGroup');

INSERT INTO app_user (email, password, first_name, last_name, active, last_password_reset_date, city, country, phone_number, workplace, company_name)
VALUES ('user1@example.com', 'password123', 'John', 'Doe', true, CURRENT_TIMESTAMP, 'City', 'Country', '+123456789012', 'Workplace', 'Company'),
       ('veljko@example.com', 'super', 'Veljko', 'Nikolic', true, CURRENT_TIMESTAMP, 'Novi Sad', 'Serbia', '+123456789012', 'Doctor', 'Poliklinika'),
       ('nikolic@example.com', 'sifra', 'Veljko', 'Nikolic', true, CURRENT_TIMESTAMP, 'Novi Sad', 'Serbia', '+381603080177', 'Administrator', 'Hemofarm'),
       ('milos@example.com', 'sifra', 'Milos', 'Djuric', true, CURRENT_TIMESTAMP, 'Novi Sad', 'Serbia', '+381123123789', 'Administrator', 'Hemofarm');

INSERT INTO public.registered_user(id)
VALUES (1),
       (2);

INSERT INTO public.company_administrator(
	company_id, id)
	VALUES (1, 3),
			(1, 4);

INSERT INTO public.equipment(name, description, type, average_rating)
	VALUES 
	('Injekcija', 'Za vakcinisanje', 'TypeA', 4.5),
	('Stetoskop', 'Za slusanje srca', 'TypeB', 3.8),
	('Toplomer', 'Za merenje temperature', 'TypeC', 2.9),
	('Cetka za zube', 'Za pranje zuba', 'TypeC', 3.5);

INSERT INTO company_uses_equipment(
	company_id, equipment_id)
	VALUES (1, 1),
		   (1, 2),
		   (1, 3),
		   (2, 4),
		   (2, 2);
