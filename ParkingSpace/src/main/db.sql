CREATE TABLE roles
(
    id SERIAL PRIMARY KEY,
    role VARCHAR(255)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       phone_number VARCHAR(15) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role_id INTEGER REFERENCES roles(id)
);

CREATE TABLE parking_slots (
                               id SERIAL PRIMARY KEY,
                               slot_number INTEGER NOT NULL UNIQUE,
                               is_available BOOLEAN DEFAULT TRUE,
                               vehicle_registration VARCHAR(20)
);

CREATE TABLE tariffs (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE vehicles (
                          id SERIAL PRIMARY KEY,
                          registration_number VARCHAR(20) NOT NULL UNIQUE,
                          model VARCHAR(30),
                          color VARCHAR(20),
                          user_id INTEGER REFERENCES users(id)
);

CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              user_id INTEGER REFERENCES users(id),
                              slot_id INTEGER REFERENCES parking_slots(id),
                              start_time TIMESTAMP NOT NULL,
                              end_time TIMESTAMP NOT NULL,
                              status VARCHAR(20) CHECK (status IN ('active', 'completed', 'cancelled')) DEFAULT 'active',
                              tariff_id INTEGER REFERENCES tariffs(id),
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO roles (id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, role) VALUES (2, 'ROLE_MANAGER');
INSERT INTO roles (id, role) VALUES (3, 'ROLE_CLIENT');
