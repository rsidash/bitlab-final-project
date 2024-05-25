insert into users (username, uuid, password, first_name, last_name, created_at)
values ('test', 'u1111', 'test', 'Name', 'Surname', now());
--
-- insert into roles (id, name, uuid, created_at)
-- values ();
--
-- insert into users_roles (user_id, role_id, created_at)
-- values ();
--
-- insert into playing_positions (id, uuid, name, code, created_at)
-- values ();
--
-- insert into job_titles (id, name, uuid, created_at)
-- values ();
--
insert into teams (name, uuid, description, user_id, created_at)
values ('TestOne', 't1111', 'desc', 1, now());
--
insert into staff (uuid, first_name, last_name, phone_number, experience, description, job_title, team_id, created_at)
values ('s1111', 'Namestaffone', 'Surnamestaffone', '87777777777', 5, 'desc', 'MANAGER', 1, now());
--
insert into players (uuid, first_name, last_name, jersey_number, phone_number, birthdate, playing_position,
team_id, created_at)
values ('p1111', 'Nameplayerone', 'Surnameplayerone', 11, '877777777', now(), 'GOALKEEPER', 1, now());