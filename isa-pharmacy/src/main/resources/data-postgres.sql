INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (1, 'Nikola', 'Nikolic', '1234567891234', 'nikolaUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usernikola@email.com', '555-555-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');

INSERT INTO PHARMACIES (id) values (1);
INSERT INTO PHARMACIES (id) values (2);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type, pharmacy_id) VALUES (2, 'Jovan', 'Jovanovic', '2234567891234', 'jovanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'userjovan@email.com', '555-333-555', true, '2017-10-01 21:58:58.508-07', 'Pharmacist', 1);

INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type) VALUES (3, 'Milan', 'Milanovic', '3234567891234', 'milanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usermilan@email.com', '555-333-333', true, '2017-10-01 21:58:58.508-07', 'Dermatologist');
INSERT INTO USERS_PHARMACIES (dermatologist_id, pharmacies_id) values (3, 1), (3, 2);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');