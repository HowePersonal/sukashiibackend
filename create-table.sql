CREATE TABLE IF NOT EXISTS users (
user_id serial PRIMARY KEY NOT NULL,
username varchar(100) NOT NULL,
password varchar(100) NOT NULL
)