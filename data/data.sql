-- ============================================================
-- 1. Curățarea Tabelelor (Ordine inversă pentru a respecta FK)
-- ============================================================
DELETE FROM tickets;
DELETE FROM duty_assignments;
DELETE FROM bus_trips;
DELETE FROM routes;
DELETE FROM buses;
DELETE FROM passengers;
DELETE FROM bus_stations;
DELETE FROM staff;

-- ============================================================
-- 2. Resetarea Auto-Increment
-- ============================================================
ALTER TABLE tickets AUTO_INCREMENT = 1;
ALTER TABLE duty_assignments AUTO_INCREMENT = 1;
ALTER TABLE bus_trips AUTO_INCREMENT = 1;
ALTER TABLE routes AUTO_INCREMENT = 1;
ALTER TABLE buses AUTO_INCREMENT = 1;
ALTER TABLE passengers AUTO_INCREMENT = 1;
ALTER TABLE bus_stations AUTO_INCREMENT = 1;
ALTER TABLE staff AUTO_INCREMENT = 1;

-- ============================================================
-- 3. Inserare BusStations (Stații)
-- ============================================================
INSERT INTO bus_stations (name, city) VALUES
                                          ('Ploiesti', 'Ploiesti'),      -- ID: 1
                                          ('Sighișoara', 'Sighisoara'),  -- ID: 2
                                          ('Cluj', 'Cluj'),              -- ID: 3
                                          ('Militari', 'Bucuresti'),     -- ID: 4
                                          ('Brasov', 'Brasov'),          -- ID: 5
                                          ('Iasi', 'Iasi'),              -- ID: 6
                                          ('Sibiu', 'Sibiu'),            -- ID: 7
                                          ('Oradea', 'Oradea'),          -- ID: 8
                                          ('Timisoara', 'Timisoara'),    -- ID: 9
                                          ('Arad', 'Arad'),              -- ID: 10
                                          ('Deva', 'Deva'),              -- ID: 11
                                          ('Targu Mures', 'Targu Mures'),-- ID: 12
                                          ('Alba Iulia', 'Alba Iulia'),  -- ID: 13
                                          ('Constanta', 'Constanta'),    -- ID: 14
                                          ('Suceava', 'Suceava'),        -- ID: 15
                                          ('Pitesti', 'Pitesti'),        -- ID: 16
                                          ('Buzau', 'Buzau'),            -- ID: 17
                                          ('Piatra Neamt', 'Piatra Neamt'), -- ID: 18
                                          ('Craiova', 'Craiova'),        -- ID: 19
                                          ('Galati', 'Galati');          -- ID: 20

-- ============================================================
-- 4. Inserare Buses (Autobuze)
-- ============================================================
INSERT INTO buses (registration_number, capacity, status, last_maintenance_date, has_accessibility_support) VALUES
                                                                                                                ('CJ32ABC', 100, 'Active', '2025-11-19', true),  -- ID: 1
                                                                                                                ('MS39ROT', 65, 'Active', '2025-09-02', true),   -- ID: 2
                                                                                                                ('MS01ROS', 50, 'Active', '2025-01-01', true),   -- ID: 3
                                                                                                                ('MS99ROS', 150, 'Active', '2025-06-15', true),  -- ID: 4
                                                                                                                ('MS49ROT', 60, 'Active', '2022-02-07', true),   -- ID: 5
                                                                                                                ('MS02ROS', 49, 'Active', '2024-01-01', false),  -- ID: 6
                                                                                                                ('MS43ROT', 38, 'Active', '2023-09-03', true),   -- ID: 7
                                                                                                                ('MS78ROT', 50, 'Active', '2024-12-02', false),  -- ID: 8
                                                                                                                ('MS24ROT', 30, 'Active', '2025-09-16', true),   -- ID: 9
                                                                                                                ('MS03ROS', 20, 'Active', '2024-01-01', true);   -- ID: 10

-- ============================================================
-- 5. Inserare Routes (Rute)
-- ============================================================
INSERT INTO routes (origin_station_id, destination_station_id, distance) VALUES
                                                                             (5, 3, 300.0),  -- ID: 1 (Brasov -> Cluj)
                                                                             (2, 3, 150.0),  -- ID: 2 (Sig -> Cluj)
                                                                             (4, 3, 500.0),  -- ID: 3 (Militari -> Cluj)
                                                                             (3, 4, 500.0),  -- ID: 4 (Cluj -> Militari)
                                                                             (8, 14, 680.0), -- ID: 5
                                                                             (9, 10, 60.0),  -- ID: 6
                                                                             (6, 18, 120.0), -- ID: 7
                                                                             (7, 13, 75.0),  -- ID: 8
                                                                             (20, 19, 300.0);-- ID: 9

-- ============================================================
-- 6. Inserare Passengers (Pasageri)
-- ============================================================
INSERT INTO passengers (name, currency, date_of_birth, requires_special_assistance) VALUES
                                                                                        ('Gheorghe', 'RON', '1999-01-01', true),          -- ID: 1
                                                                                        ('Vasile', 'EUR', '2002-02-02', false),           -- ID: 2
                                                                                        ('Alina Munteanu', 'RON', '1994-05-12', false),   -- ID: 3
                                                                                        ('Bogdan Pop', 'RON', '1988-03-03', false),       -- ID: 4
                                                                                        ('Camelia Iliescu', 'RON', '1990-07-21', false),  -- ID: 5
                                                                                        ('Daniel Georgescu', 'EUR', '1979-11-11', false), -- ID: 6
                                                                                        ('Elena Bălan', 'RON', '1985-02-17', true),       -- ID: 7
                                                                                        ('Florin Radu', 'RON', '1996-09-09', false),      -- ID: 8
                                                                                        ('Gabriela Costin', 'EUR', '2000-12-12', false),  -- ID: 9
                                                                                        ('Ioana Matei', 'RON', '1992-08-08', false);      -- ID: 10

-- ============================================================
-- 7. Inserare Staff (Driver & TripManager)
-- ============================================================
-- NOTĂ: 'license_acquired_date' înlocuiește 'yearsOfExperience'
-- NOTĂ: 'employee_code' este doar pentru Manageri
INSERT INTO staff (staff_type, name, license_acquired_date, employee_code) VALUES
                                                                               ('DRIVER', 'Sofer1', '2013-01-01', null),         -- ID: 1 (12 ani exp)
                                                                               ('DRIVER', 'Sofer2', '2013-01-01', null),         -- ID: 2 (12 ani exp)
                                                                               ('DRIVER', 'Sofer3', '2010-01-01', null),         -- ID: 3 (15 ani exp)
                                                                               ('DRIVER', 'Ion Popescu', '2017-01-01', null),    -- ID: 4 (8 ani exp)
                                                                               ('DRIVER', 'Maria Ionescu', '2019-01-01', null),  -- ID: 5 (6 ani exp)
                                                                               ('DRIVER', 'George Enache', '2021-01-01', null),  -- ID: 6 (4 ani exp)
                                                                               ('DRIVER', 'Ana Vasilescu', '2015-01-01', null),  -- ID: 7 (10 ani exp)
                                                                               ('DRIVER', 'Cristian Dumitrescu', '2023-01-01', null), -- ID: 8 (2 ani exp)
                                                                               ('DRIVER', 'Elena Marin', '2019-01-01', null),    -- ID: 9 (6 ani exp)
                                                                               ('DRIVER', 'Mihai Georgescu', '2020-01-01', null);-- ID: 10 (5 ani exp)

INSERT INTO staff (staff_type, name, license_acquired_date, employee_code) VALUES
                                                                               ('MANAGER', 'Manager1', null, 'MGR-007'),        -- ID: 11
                                                                               ('MANAGER', 'Manager2', null, 'MGR-002'),        -- ID: 12
                                                                               ('MANAGER', 'Andrei Petrescu', null, 'MGR-010'), -- ID: 13
                                                                               ('MANAGER', 'Eliza Popa', null, 'MGR-011'),      -- ID: 14
                                                                               ('MANAGER', 'Victor Stan', null, 'MGR-012'),     -- ID: 15
                                                                               ('MANAGER', 'Cristina Neagu', null, 'MGR-013'),  -- ID: 16
                                                                               ('MANAGER', 'Ionut Ionescu', null, 'MGR-014'),   -- ID: 17
                                                                               ('MANAGER', 'Laura Dumitru', null, 'MGR-015'),   -- ID: 18
                                                                               ('MANAGER', 'Bogdan Marinescu', null, 'MGR-016'),-- ID: 19
                                                                               ('MANAGER', 'Alina Ciobanu', null, 'MGR-017');   -- ID: 20

-- ============================================================
-- 8. Inserare BusTrips (Curse)
-- ============================================================
INSERT INTO bus_trips (route_id, bus_id, start_time, available_seats, base_price, status) VALUES
                                                                                              (4, 3, '09:30:00', 50, 200.0, 'Planned'),  -- ID: 1
                                                                                              (1, 6, '12:30:00', 30, 120.0, 'Planned'),  -- ID: 2
                                                                                              (2, 3, '16:30:00', 45, 99.0,  'Planned'),  -- ID: 3
                                                                                              (3, 4, '07:00:00', 140, 250.0, 'Planned'), -- ID: 4
                                                                                              (4, 1, '13:45:00', 100, 210.0, 'Planned'), -- ID: 5
                                                                                              (2, 9, '18:15:00', 30, 95.0,  'Planned'),  -- ID: 6
                                                                                              (3, 4, '20:00:00', 145, 270.0, 'Planned'), -- ID: 7
                                                                                              (1, 7, '06:30:00', 38, 115.0, 'Planned'),  -- ID: 8
                                                                                              (4, 10, '11:15:00', 20, 190.0, 'Planned'), -- ID: 9
                                                                                              (2, 8, '22:30:00', 50, 100.0, 'Planned');  -- ID: 10

-- ============================================================
-- 9. Inserare DutyAssignments (Alocări)
-- ============================================================
INSERT INTO duty_assignments (bus_trip_id, staff_id, role) VALUES
                                                               (1, 1, 'PrimaryDriver'),  -- ID: 1
                                                               (1, 2, 'ReserveDriver'),  -- ID: 2
                                                               (2, 3, 'PrimaryDriver'),  -- ID: 3
                                                               (3, 4, 'PrimaryDriver'),  -- ID: 4
                                                               (3, 5, 'ReserveDriver'),  -- ID: 5
                                                               (4, 6, 'PrimaryDriver'),  -- ID: 6
                                                               (5, 7, 'PrimaryDriver'),  -- ID: 7
                                                               (6, 8, 'PrimaryDriver'),  -- ID: 8
                                                               (7, 9, 'PrimaryDriver'),  -- ID: 9
                                                               (9, 10, 'ReserveDriver'); -- ID: 10

-- ============================================================
-- 10. Inserare Tickets (Bilete)
-- ============================================================
-- CORECTAT: passenger_trip_id în loc de passenger_id
INSERT INTO tickets (bus_trip_id, passenger_trip_id, seat_number, price) VALUES
                                                                             (2, 1, '17', 0.0),    -- ID: 1
                                                                             (1, 1, '4', 0.0),     -- ID: 2
                                                                             (4, 3, '10', 250.0),  -- ID: 3
                                                                             (5, 4, '5', 210.0),   -- ID: 4
                                                                             (3, 5, '2', 99.0),    -- ID: 5
                                                                             (9, 6, '1', 190.0),   -- ID: 6
                                                                             (7, 7, '12', 270.0),  -- ID: 7
                                                                             (8, 8, '8', 115.0),   -- ID: 8
                                                                             (10, 9, '20', 100.0), -- ID: 9
                                                                             (8, 10, '3', 115.0);  -- ID: 10