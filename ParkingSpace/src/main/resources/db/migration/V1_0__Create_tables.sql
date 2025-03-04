CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL UNIQUE,
    code INTEGER
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    role_id INTEGER REFERENCES roles(id),
    login VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE vehicles (
    id SERIAL PRIMARY KEY,
    vehicle_registration_number VARCHAR(20) NOT NULL UNIQUE,
    vehicle_model VARCHAR(30),
    vehicle_color VARCHAR(20),
    user_id INTEGER REFERENCES users(id)
);

CREATE TABLE parking_slots (
    id SERIAL PRIMARY KEY,
    slot_code VARCHAR(20) NOT NULL UNIQUE,
    is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE reservations (
    id SERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    vehicle_id INTEGER REFERENCES vehicles(id),
    parking_slot_id INTEGER REFERENCES parking_slots(id),
    status VARCHAR(20) DEFAULT 'active',
    price DOUBLE PRECISION
);