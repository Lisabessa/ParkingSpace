CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       vehicle_registration_number VARCHAR(20) NOT NULL UNIQUE,
                       vehicle_model VARCHAR(30),
                       vehicle_color VARCHAR(20),
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       phone_number VARCHAR(15) NOT NULL
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
                              user_id INTEGER REFERENCES users(id),
                              parking_slot_id INTEGER REFERENCES parking_slots(id),
                              status VARCHAR(20) DEFAULT 'active',
                              price DOUBLE PRECISION
);