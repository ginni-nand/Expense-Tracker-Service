CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  age INT,
  email VARCHAR UNIQUE
);