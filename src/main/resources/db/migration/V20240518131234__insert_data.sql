CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--
insert into users (username, uuid, password, first_name, last_name, created_at)
values ('admin', uuid_generate_v4(), '$2a$12$Fhlc3De.cHJpk7Zi/ucfLeqXm0TZ4rflloTGZHv9wwP2yWRtDO6LG', 'Gary', 'Bettman', now());
--
insert into users (username, uuid, password, first_name, last_name, created_at)
values ('samueli', uuid_generate_v4(), '$2a$12$jDqFMHw4y1h8hf7NltQyI.hqtB5g34FNWoq3KOfuhs2BU72sR/OnW', 'Henry', 'Samueli', now());
--
insert into roles (name, uuid, created_at)
values ('ROLE_ADMIN', uuid_generate_v4(), now());
--
insert into roles (name, uuid, created_at)
values ('ROLE_USER', uuid_generate_v4(), now());
--
insert into users_roles (user_id, role_id, created_at)
values (1, 1, now());
--
insert into users_roles (user_id, role_id, created_at)
values (2, 2, now());
--
insert into teams (name, uuid, description, user_id, created_at)
values ('Anaheim Ducks', uuid_generate_v4(), 'The Anaheim Ducks are a professional ice hockey team based in Anaheim, California. The Ducks compete in the Western Conference of the National Hockey League (NHL) as a member of the Pacific Division, and play their home games at Honda Center.', 2, now());
--
insert into staff (uuid, first_name, last_name, phone_number, experience, description, job_title, team_id, created_at)
values (uuid_generate_v4(), 'Pat', 'Verbeek', '87777777777', 5, 'Patrick Martin Verbeek is a Canadian former professional ice hockey player and current general manager of the Anaheim Ducks', 'MANAGER', 1, now());
--
insert into staff (uuid, first_name, last_name, phone_number, experience, description, job_title, team_id, created_at)
values (uuid_generate_v4(), 'Greg', 'Cronin', '87777777777', 11, 'is an American professional ice hockey coach who is the head coach for the Anaheim Ducks', 'COACH', 1, now());
--
insert into players (uuid, first_name, last_name, jersey_number, phone_number, birthdate, playing_position,
                    team_id, created_at)
values (uuid_generate_v4(), 'Lukas', 'Dostal', 1, '877777777', '2000-06-22', 'GOALKEEPER', 1, now());
--
insert into players (uuid, first_name, last_name, jersey_number, phone_number, birthdate, playing_position,
                     team_id, created_at)
values (uuid_generate_v4(), 'Trevor', 'Zegras', 11, '8717777777', '2001-03-20', 'CENTER_FORWARD', 1, now());