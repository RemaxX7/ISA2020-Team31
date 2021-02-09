INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (2, 'Nikola', 'Nikolic', '1234567891234', 'nikolaUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+nikolaUser@gmail.com', '555-555-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (6, 'Petar', 'Petrovic', '5234567891234', 'petarUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+petarUser@gmail.com', '333-333-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (7, 'Marko', 'Markovic', '8234567891234', 'markoUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+markoUser@gmail.com', '333-333-999', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (8, 'Nenad', 'Pirlo', '9234567891234', 'nenadUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'isa.pharmacy.31+nenadUser@gmail.com', '333-333-998', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');
INSERT INTO PHARMACIES (id) values (1);
INSERT INTO PHARMACIES (id) values (2);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type, pharmacy_id) VALUES (3, 'Jovan', 'Jovanovic', '2234567891234', 'jovanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'userjovan@email.com', '555-333-555', true, '2017-10-01 21:58:58.508-07', 'Pharmacist', 1);

INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type) VALUES (4, 'Milan', 'Milanovic', '3234567891234', 'milanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usermilan@email.com', '555-333-333', true, '2017-10-01 21:58:58.508-07', 'Dermatologist');
INSERT INTO USERS_PHARMACIES (dermatologist_id, pharmacies_id) values (3, 1), (3, 2);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');

INSERT INTO MEDICINE (id,name,type,shape,composition,manufacturer,issuing) values (2,'Midol',3,2,'midol 200g','SlaFarm',1);
INSERT INTO MEDICINE (id,name,type,shape,composition,manufacturer,issuing) values (3,'Andol',2,1,'andol 200g','SlaFarm',1);
INSERT INTO MEDICINE (id,name,type,shape,composition,manufacturer,issuing) values (4,'Fenidol',1,3,'fenidol 200g','SlaFarm',1);

INSERT INTO appointments (appointment_status,patient_id,type) values (1,2,'Exam');
INSERT INTO appointments (appointment_status,patient_id,type) values (1,6,'Exam');
INSERT INTO appointments (appointment_status,patient_id,type) values (1,6,'Counseling');
INSERT INTO MEDICINE (id, name, shape, type, composition, additional_notes, manufacturer, issuing) values (1, 'Paracetamol', 0, 0, 'Kompozicija paracetamola', 'Dodatne informacije', 'Galenika', 0);

INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (1, 'adasdasdasd', 3, '2020-02-02', 130, 6, 1);
INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (1, 3, 1);
INSERT INTO MEDICINE_RESERVATIONS_MEDICINE_RESERVATION_ITEMS (medicine_reservation_id, medicine_reservation_items_id) values (1, 1);
INSERT INTO REVIEWS (type, score, patient_id, medicine_id) values ('Medicine', 5, 6, 1);

INSERT INTO MEDICINE_RESERVATIONS (id, code, medicine_reservation_status, pick_up_date, price, patient_id, pharmacy_id) values (2, 'dafasfasfasf', 3, '2020-02-02', 130, 7, 1);
INSERT INTO MEDICINE_RESERVATION_ITEMS (id, quantity, medicine_id) values (2, 3, 1);
INSERT INTO MEDICINE_RESERVATIONS_MEDICINE_RESERVATION_ITEMS (medicine_reservation_id, medicine_reservation_items_id) values (2, 2);
INSERT INTO REVIEWS (type, score, patient_id, medicine_id) values ('Medicine', 4, 7, 1);

INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 3, '2020-02-02 13:30:00', '2020-03-02 13:00:00', 100, 'Report', 7, 1, 2);
						
INSERT INTO APPOINTMENTS (type, appointment_status, end_date_time, start_date_time, price, report, patient_id, pharmacy_id, pharmacist_id) values
						('Counseling', 3, '2020-01-01 13:30:00', '2020-01-01 13:00:00', 100, 'Report', 8, 1, 2);
INSERT INTO REVIEWS (type, score, patient_id, employee_id) values ('Employee', 5, 4, 2);

INSERT INTO COUNTRIES (name, code) values ('Serbia', 'RS'), ('Norway', 'NO');

INSERT INTO CITIES(name, country_id) values ('Novi Sad', 1), ('Belgrade', 1);
