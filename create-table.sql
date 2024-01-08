CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY NOT NULL,
    email varchar(100) NOT NULL,
    username varchar(100) NOT NULL,
    password varchar(100)
)