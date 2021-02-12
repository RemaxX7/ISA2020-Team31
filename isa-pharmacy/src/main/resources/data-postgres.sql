INSERT INTO COUNTRIES (name, code) values ('Serbia', 'RSD'), ('Norway', 'NO');
INSERT INTO CITIES(name, country_id) values ('Novi Sad', 1), ('Belgrade', 1);

INSERT INTO ADDRESSES(street, number, city_id, latitude, longitude) values ('Bulevar Oslobodjenja', '50', 1, 45, 45);
INSERT INTO ADDRESSES(street, number, city_id, latitude, longitude) values ('Bulevar Evrope', '50', 1, 45, 45);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Benu', 1, 4.5);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Jankovic', 2, 4.9);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Asd', 1, 2.8);
INSERT INTO PHARMACIES (name, address_id, rate) values ('asdf', 2, 2);
INSERT INTO PHARMACIES (name, address_id, rate) values ('gggg', 1, 4.9);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Asgfdd', 1, 1);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Bdfgenu', 2, 4.5);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Jangfdkovic', 1, 4.9);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Adfggsd', 1, 4.9);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Behgfghjnu', 1, 4.5);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Jankojs44vic', 1, 4.9);
INSERT INTO PHARMACIES (name, address_id, rate) values ('A345sd', 1, 4.9);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Ben5gsu', 2, 4.5);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Jansdfkovic', 1, 4.9);
INSERT INTO PHARMACIES (name, address_id, rate) values ('Ajerhjsd', 1, 3);

INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (5, 'Nikola', 'Nikolic', '1234567891234', 'nikolaUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+nikolaUser@gmail.com', '555-555-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (4, 'Petar', 'Petrovic', '4234567891234', 'petarUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+petarUser@gmail.com', '333-333-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');

INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type, pharmacy_id) VALUES (2, 'Jovan', 'Jovanovic', '2234567891234', 'jovanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'userjovan@email.com', '555-333-555', true, '2017-10-01 21:58:58.508-07', 'Pharmacist', 1);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type, pharmacy_id) VALUES (6, 'Milos', 'Mirkovic', '1134567891234', 'milosUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+milosUser@gmail.com', '555-333-555', true, '2017-10-01 21:58:58.508-07','PharmacyAdmin', 1);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type) VALUES (3, 'Milan', 'Milanovic', '3234567891234', 'milanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usermilan@email.com', '555-333-333', true, '2017-10-01 21:58:58.508-07', 'Dermatologist');
INSERT INTO USERS_PHARMACIES (dermatologist_id, pharmacies_id) values (3, 1), (3, 2);

INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (3, 'ROLE_PHARMACIST');
INSERT INTO AUTHORITY (id, name) VALUES (4, 'ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (id, name) VALUES (5, 'ROLE_PHARMACY_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_id) values (5, 1), (4, 1),(6,5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) values (2, 3), (3, 4);

INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (1, 'Paracetamol', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (2, 'asfasfas', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (3, 'asdahjgj', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (4, 'dfdhdgj', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (5, 'Xanax', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (6, 'afadgsdg', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (7, 'Andol', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (8, 'Aspirin', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (9, 'gfjfghj', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (10, 'hfghfgh', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (11, 'hfgsaasfd', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (12, 'jsdgsdg', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (13, 'sdfsdg', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (14, 'hgfdgjfhk', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (15, 'hhhjssdf', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);

INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (1, 'adasdasdasd', 3, '2020-02-02', 130, 5, 1);
INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (1, 3, 1);
INSERT INTO MEDICINE_RESERVATIONS_MEDICINE_RESERVATION_ITEMS (medicine_reservation_id, medicine_reservation_items_id) values (1, 1);
INSERT INTO REVIEWS (type, score, patient_id, medicine_id) values ('Medicine', 5, 5, 1);

INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (2, 'dafasfasfasf', 3, '2020-02-02', 130, 4, 1);
INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (2, 3, 1);
INSERT INTO MEDICINE_RESERVATIONS_MEDICINE_RESERVATION_ITEMS (medicine_reservation_id, medicine_reservation_items_id) values (2, 2);
INSERT INTO REVIEWS (type, score, patient_id, medicine_id) values ('Medicine', 4, 4, 1);
INSERT INTO REVIEWS (type, score, patient_id, pharmacy_id) values ('Pharmacy', 5, 5, 1);
INSERT INTO REVIEWS (type, score, patient_id, pharmacy_id) values ('Pharmacy', 0, 4, 1);


INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (3, '6165146', 0, '2022-02-02', 130, 5, 1);
INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (3, 3, 1);
INSERT INTO MEDICINE_RESERVATIONS_MEDICINE_RESERVATION_ITEMS (medicine_reservation_id, medicine_reservation_items_id) values (3, 3);
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 3, '2020-02-02 13:30:00', '2020-03-02 13:00:00', 100, 'Report', 5, 1, 2);
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2021-02-02 13:30:00', '2021-02-02 13:00:00', 100, 'Reportt', 5, 1, 3);	
		INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 1, '2021-09-09 16:30:00', '2021-09-09 17:00:00', 100, 'Report2', 5, 1, 2);	                        
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 3, '2020-01-01 13:30:00', '2020-01-01 13:00:00', 100, 'Report', 4, 1, 2);
INSERT INTO REVIEWS (type, score, patient_id, employee_id) values ('Employee', 5, 4, 2);