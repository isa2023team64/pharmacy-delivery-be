-- users
INSERT INTO app_user(
	id, username, email, password)
	VALUES (1, 'veljko', 'nikolicveljko01@gmail.com', 'super'),
		   (2, 'marko', 'marko@gmail.com', 'super');

-- registered users
INSERT INTO registered_user(
	id, first_name, last_name)
    VALUES (1, 'Veljko', 'Nikolic'),
		   (2, 'Marko', 'Nikolic');
