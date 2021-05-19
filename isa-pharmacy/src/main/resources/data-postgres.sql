INSERT INTO COUNTRIES (name, code) values ('Serbia', 'RS');
INSERT INTO CITIES(name, country_id) values ('Novi Sad', 1), ('Belgrade', 1), ('Nis', 1);
INSERT INTO ADDRESSES(street, number, city_id, latitude, longitude) values ('Bulevar Oslobodjenja', '50', 1, 45, 45);
INSERT INTO ADDRESSES(street, number, city_id, latitude, longitude) values ('Bulevar Evrope', '50', 1, 45, 45);
INSERT INTO ADDRESSES(street, number, city_id, latitude, longitude) values ('Trg Dositeja Obradovica', 6, 1, 45.24577941377878, 19.850995591105743);
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

INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type, address_id) VALUES (5, 'Nikola', 'Nikolic', '1234567891234', 'nikolaUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+nikolaUser@gmail.com', '555-555-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient', 3);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type, address_id) VALUES (4, 'Petar', 'Petrovic', '4234567891234', 'petarUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+petarUser@gmail.com', '333-333-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient', 3);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type, address_id) VALUES (44, 'Marko', 'Rak', '9812368798712', 'markoUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+markoUser@gmail.com', '333-333-951', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient', 3);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type, pharmacy_id) VALUES (2, 'Jovan', 'Jovanovic', '2234567891234', 'jovanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+userJovan@gmail.com', '555-333-555', true, '2017-10-01 21:58:58.508-07', 'Pharmacist', 1);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type, pharmacy_id) VALUES (6, 'Milos', 'Mirkovic', '1134567891234', 'milosUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+milosUser@gmail.com', '555-333-555', true, '2017-10-01 21:58:58.508-07','PharmacyAdmin', 1);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type) VALUES (3, 'Milan', 'Milanovic', '3234567891234', 'milanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usermilan@email.com', '555-333-333', true, '2017-10-01 21:58:58.508-07', 'Dermatologist');
INSERT INTO USERS_PHARMACIES (dermatologist_id, pharmacies_id) values (3, 1), (3, 2);

INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (3,'ROLE_PHARMACIST');
INSERT INTO USER_AUTHORITY (user_id, authority_id) values (5, 1), (4, 1),(44,1);
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

INSERT INTO REVIEWS (type, score, patient_id, medicine_id) values ('Medicine', 5, 5, 1);

INSERT INTO REVIEWS (type, score, patient_id, medicine_id) values ('Medicine', 4, 4, 1);
INSERT INTO REVIEWS (type, score, patient_id, pharmacy_id) values ('Pharmacy', 5, 5, 1);
INSERT INTO REVIEWS (type, score, patient_id, pharmacy_id) values ('Pharmacy', 0, 4, 1);

INSERT INTO INVENTORY_ITEM(id,quantity,medicine_id) values (100,3,5);
INSERT INTO INVENTORY_ITEM(id,quantity,medicine_id) values (200,5,8);

INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 3, '2021-01-12 16:30:00', '2021-02-12 16:05:00', 105, 'Report', 5, 1, 3);
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2021-02-03 13:30:00', '2021-02-03 13:00:00', 100, 'Report', 5, 7, 3);
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 3, '2020-02-03 12:10:00', '2020-02-03 11:30:00', 12, 'Report', 5, 6, 3);	
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2019-03-03 13:30:00', '2019-03-03 13:10:00', 55, 'Report', 5, 5, 3);	
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2020-04-04 12:30:00', '2020-04-04 11:00:00', 50, 'Report', 5, 4, 3);	
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2021-02-12 13:30:00', '2021-02-12 13:00:00', 88, 'Report', 5, 3, 3);	
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2018-01-11 13:30:00', '2018-02-03 12:45:00', 190, 'Report', 5, 2, 3);	
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2021-11-11 13:30:00', '2021-11-11 12:45:00', 190, 'Report', 5, 2, 3);	
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, dermatologist_id) values
						('Exam', 1, '2021-05-16 13:30:00', '2021-05-16 12:45:00', 190, 'Report', 5, 2, 3);


						
INSERT INTO APPOINTMENT_MEDICINE_ITEMS (id, quantity, medicine_id) values (111, 2, 5);
INSERT INTO APPOINTMENT_MEDICINE_ITEMS (id, quantity, medicine_id) values (222, 3, 1);
INSERT INTO APPOINTMENT_MEDICINE_ITEMS (id, quantity, medicine_id) values (333, 3, 2);
INSERT INTO APPOINTMENT_MEDICINE_ITEMS (id, quantity, medicine_id) values (444, 3, 4);

INSERT INTO APPOINTMENTS_APPOINTMENT_MEDICINE_ITEMS (appointment_id, appointment_medicine_items_id) values (2, 111);	
INSERT INTO APPOINTMENTS_APPOINTMENT_MEDICINE_ITEMS (appointment_id, appointment_medicine_items_id) values (2, 222);	

INSERT INTO APPOINTMENTS_APPOINTMENT_MEDICINE_ITEMS (appointment_id, appointment_medicine_items_id) values (1, 333);	
INSERT INTO APPOINTMENTS_APPOINTMENT_MEDICINE_ITEMS (appointment_id, appointment_medicine_items_id) values (1, 444);	

INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 3, '2020-01-01 13:30:00', '2020-01-01 13:00:00', 100, 'Report', 4, 1, 2);
INSERT INTO REVIEWS (type, score, patient_id, employee_id) values ('Employee', 5, 4, 2);

INSERT INTO PATIENTS_ALLERGIES (patient_id, allergies_id) values (5, 1), (5, 2), (5, 3), (5, 7), (4, 1), (4,2), (4,6);

INSERT INTO SHIFTS (end_date_time, start_date_time, employee_id, pharmacy_id) values ('2022-06-20 00:00:00', '2021-02-15 00:00:00', 2, 1);
INSERT INTO SHIFTS (end_date_time, start_date_time, employee_id, pharmacy_id) values ('2022-06-20 00:00:00', '2021-02-15 00:00:00', 3, 1);
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report,patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 1, '2021-02-16 13:29:00', '2021-02-16 13:00:00', 100, 'Report',5, 1, 2);
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report,patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 1, '2021-10-10 13:29:00', '2021-10-10 13:00:00', 100, 'Report',5, 1, 2);
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report,patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 1, '2021-05-15 13:29:00', '2021-05-15 13:00:00', 100, 'Report',5, 1, 2);

INSERT INTO LOYALTY_PROGRAM_DISCOUNT (loyalty_program_category, percentage) VALUES (0, 5);
INSERT INTO LOYALTY_PROGRAM_DISCOUNT (loyalty_program_category, percentage) VALUES (1, 10);
INSERT INTO LOYALTY_PROGRAM_DISCOUNT (loyalty_program_category, percentage) VALUES (2, 15);
INSERT INTO LOYALTY_PROGRAM_DISCOUNT (loyalty_program_category, percentage) VALUES (3, 10);
INSERT INTO LOYALTY_PROGRAM_DISCOUNT (loyalty_program_category, percentage) VALUES (4, 15);

INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (1111, 3, 1);
INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (2222, 3, 1);
INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (3333, 3, 1);

INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (112, 'adasdasdasd', 0, '2020-02-02', 130, 5, 1);
INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (2222, 'dafasfasfasf', 3, '2020-02-02', 130, 4, 1);
INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (3333, '6165146', 0, '2022-02-02', 130, 5, 1);


