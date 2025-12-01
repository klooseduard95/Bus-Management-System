-- ============================================================
-- 1. Curățarea Tabelelor
-- ============================================================
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM tickets;
DELETE FROM duty_assignments;
DELETE FROM bus_trips;
DELETE FROM routes;
DELETE FROM buses;
DELETE FROM passengers;
DELETE FROM bus_stations;
DELETE FROM drivers;
DELETE FROM trip_managers;

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
ALTER TABLE drivers AUTO_INCREMENT = 1;
ALTER TABLE trip_managers AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 1;

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
-- 7. Inserare Staff (Drivers & Managers)
-- ============================================================
INSERT INTO drivers (name, license_acquired_date) VALUES
                                                      ('Sofer1', '2013-01-01'),         -- ID: 1
                                                      ('Sofer2', '2013-01-01'),         -- ID: 2
                                                      ('Sofer3', '2010-01-01'),         -- ID: 3
                                                      ('Ion Popescu', '2017-01-01'),    -- ID: 4
                                                      ('Maria Ionescu', '2019-01-01'),  -- ID: 5
                                                      ('George Enache', '2021-01-01'),  -- ID: 6
                                                      ('Ana Vasilescu', '2015-01-01'),  -- ID: 7
                                                      ('Cristian Dumitrescu', '2023-01-01'), -- ID: 8
                                                      ('Elena Marin', '2019-01-01'),    -- ID: 9
                                                      ('Mihai Georgescu', '2020-01-01');-- ID: 10

INSERT INTO trip_managers (name, employee_code) VALUES
                                                    ('Manager1', 'MGR-007'),        -- ID: 1
                                                    ('Manager2', 'MGR-002'),        -- ID: 2
                                                    ('Andrei Petrescu', 'MGR-010'), -- ID: 3
                                                    ('Eliza Popa', 'MGR-011'),      -- ID: 4
                                                    ('Victor Stan', 'MGR-012'),     -- ID: 5
                                                    ('Cristina Neagu', 'MGR-013'),  -- ID: 6
                                                    ('Ionut Ionescu', 'MGR-014'),   -- ID: 7
                                                    ('Laura Dumitru', 'MGR-015'),   -- ID: 8
                                                    ('Bogdan Marinescu', 'MGR-016'),-- ID: 9
                                                    ('Alina Ciobanu', 'MGR-017');   -- ID: 10

-- ============================================================
-- 8. Inserare BusTrips (Curse)
-- ============================================================
INSERT INTO bus_trips (route_id, bus_id, start_time, available_seats, base_price, status) VALUES
                                                                                              (4, 3, '09:30:00', 49, 200.0, 'Planned'),  -- ID: 1 (Era 50, are 1 bilet)
                                                                                              (1, 6, '12:30:00', 29, 120.0, 'Planned'),  -- ID: 2 (Era 30, are 1 bilet)
                                                                                              (2, 3, '16:30:00', 44, 99.0,  'Planned'),  -- ID: 3 (Era 45, are 1 bilet)
                                                                                              (3, 4, '07:00:00', 139, 250.0, 'Planned'), -- ID: 4 (Era 140, are 1 bilet)
                                                                                              (4, 1, '13:45:00', 99, 210.0, 'Planned'),  -- ID: 5 (Era 100, are 1 bilet)
                                                                                              (2, 9, '18:15:00', 30, 95.0,  'Planned'),  -- ID: 6 (Fără bilete)
                                                                                              (3, 4, '20:00:00', 144, 270.0, 'Planned'), -- ID: 7 (Era 145, are 1 bilet)
                                                                                              (1, 7, '06:30:00', 36, 115.0, 'Planned'),  -- ID: 8 (Era 38, are 2 bilete)
                                                                                              (4, 10, '11:15:00', 19, 190.0, 'Planned'), -- ID: 9 (Era 20, are 1 bilet)
                                                                                              (2, 8, '22:30:00', 49, 100.0, 'Planned');  -- ID: 10 (Era 50, are 1 bilet)

-- ============================================================
-- 9. Inserare DutyAssignments (Alocări)
-- ============================================================
INSERT INTO duty_assignments (bus_trip_id, driver_id, manager_id, role) VALUES
                                                                            (1, 1, NULL, 'PrimaryDriver'),  -- ID: 1
                                                                            (1, 2, NULL, 'ReserveDriver'),  -- ID: 2
                                                                            (2, 3, NULL, 'PrimaryDriver'),  -- ID: 3
                                                                            (3, 4, NULL, 'PrimaryDriver'),  -- ID: 4
                                                                            (3, 5, NULL, 'ReserveDriver'),  -- ID: 5
                                                                            (4, 6, NULL, 'PrimaryDriver'),  -- ID: 6
                                                                            (5, 7, NULL, 'PrimaryDriver'),  -- ID: 7
                                                                            (6, 8, NULL, 'PrimaryDriver'),  -- ID: 8
                                                                            (7, 9, NULL, 'PrimaryDriver'),  -- ID: 9
                                                                            (9, 10, NULL, 'ReserveDriver'), -- ID: 10
                                                                            (10, NULL, 1, 'PrimaryDriver'); -- ID: 11 (Un Manager asignat)

-- ============================================================
-- 10. Inserare Tickets (Bilete)
-- ============================================================
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