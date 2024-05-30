CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE
);

CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    created_at DATE NOT NULL,
    updated_at DATE
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    created_at DATE DEFAULT CURRENT_DATE,
    updated_at DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS teams (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    user_id INT NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS staff (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    experience SMALLINT,
    description TEXT,
    job_title VARCHAR(255) NOT NULL,
    team_id INT NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE,
    FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE IF NOT EXISTS players (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    jersey_number SMALLINT,
    phone_number VARCHAR(20),
    birthdate DATE,
    playing_position VARCHAR(255),
    team_id INT NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE,
    FOREIGN KEY (team_id) REFERENCES teams(id)
);
