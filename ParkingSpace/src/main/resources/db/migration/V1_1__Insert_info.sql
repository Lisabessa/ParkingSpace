INSERT INTO roles (role, code)
VALUES
('ADMIN', 777),
('CLIENT', 700);


INSERT INTO users (first_name, last_name, phone_number, role_id, login, password)
VALUES
('Dominic', 'Toretto', '+1(555)123-4567', 2, 'dominic', '123456'),
('Brian', 'Connor', '+1(555)987-6543', 2, 'brian', '123456'),
('Han', 'Lue', '+1(555)963-2580', 2, 'han', '123456'),
('Letty', 'Ortiz', '+1(555)246-8102', 2, 'letty', '123456'),
('Roman', 'Pearce', '+1(555)369-1470', 2, 'roman', '123456'),
('Tej', 'Parker', '+1(555)159-7534', 2, 'tej', '123456'),
('Mia', 'Toretto', '+1(555)741-8529', 2, 'mia', '123456'),
('Gisele', 'Yashar', '+1(555)357-9162', 2, 'gisele', '123456'),
('Luke', 'Hobbs', '+1(555)918-3726', 2, 'luke', '123456'),
('Deckard', 'Shaw', '+1(555)827-4935', 2, 'statham', '123456'),
('Owen', 'Shaw', '+1(555)493-8261', 2, 'owen', '123456');


INSERT INTO vehicles (vehicle_registration_number, vehicle_model, vehicle_color, user_id)
VALUES
('FT-0001', 'Dodge Charger', 'Black', 1),
('BOC-1979', 'Nissan Skyline GT-R', 'Blue', 2),
('HSO-2000', 'Mazda RX-7 FD', 'Orange', 3),
('LOR-2015', 'Plymouth Road Runner', 'Blue', 4),
('RPE-1998', 'Mitsubishi Eclipse', 'Blue', 5),
('TP-2006', 'Subaru Impreza', 'Green', 6),
('MTO-2001', 'Honda Civic', 'Red', 7),
('GYA-2007', 'Jaguar XJ', 'White', 8),
('LH-2011', 'Chevrolet Camaro', 'Golden', 9),
('DS-2015', 'Ford Mustang', 'Silver', 10),
('OS-2013', 'Audi R8', 'Black', 11),
('DOM-1969', 'Dodge Charger Daytona', 'Red', 1),
('DOM-1993', 'Honda Civic EJ1', 'Brown', 1);


INSERT INTO parking_slots (slot_code, is_available)
VALUES
('A1', TRUE),
('A2', TRUE),
('A3', TRUE),
('A4', TRUE),
('A5', TRUE),
('B1', TRUE),
('B2', TRUE),
('B3', TRUE),
('B4', TRUE),
('B5', TRUE),
('C1', TRUE),
('C2', TRUE),
('C3', TRUE),
('C4', TRUE),
('C5', TRUE);