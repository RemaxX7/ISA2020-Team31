INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, penalty, enabled, last_password_reset_date, type) VALUES (1, 'Nikola', 'Nikolic', '1234567891234', 'nikolaUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usernikola@email.com', '555-555-555', 0,  true, '2017-10-01 21:58:58.508-07', 'Patient');

INSERT INTO COUNTRY (id,code,name) VALUES (1,'SR','Srbija');
INSERT INTO CITY (id,name,country_id) values(1,'Novi Sad',1);
INSERT INTO ADDRESS (id,latitude,longitude,street,number,city_id) values (1,0.0,0.0,'Bulevar Mihajla Pupuna',10,1);
INSERT INTO ADDRESS (id,latitude,longitude,street,number,city_id) values (2,0.0,0.0,'Bulevar Oslobodjenja',112,1);

INSERT INTO PHARMACIES (id,name,rate,address_id) values (1,'Apoteka Jankovic',4.3,1);
INSERT INTO PHARMACIES (id,name,rate,address_id) values (2,'Apoteka Benu',3.7,2);

INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type,address_id, pharmacy_id) VALUES (2, 'Jovan', 'Jovanovic', '2234567891234', 'jovanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'userjovan@email.com', '555-333-555', true, '2017-10-01 21:58:58.508-07', 'Pharmacist',1, 1);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type,address_id, pharmacy_id) VALUES (3, 'Ivan', 'Ivanovic', '2134567891234', 'ivanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'userivan@email.com', '555-222-555', true, '2018-10-01 21:58:58.508-07', 'Pharmacist',2, 1);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type,address_id, pharmacy_id) VALUES (4, 'Ana', 'Ivanovic', '2434567891234', 'anaUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'userana@email.com', '555-111-555', true, '2018-10-01 21:58:58.508-07', 'Pharmacist',1, 2);

INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type,address_id) VALUES (5, 'Milan', 'Milanovic', '3234567891234', 'milanUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usermilan@email.com', '555-333-333', true, '2017-10-01 21:58:58.508-07', 'Dermatologist',1);
INSERT INTO USERS (id, name, surname, uidn, username, password, email, phone_number, enabled, last_password_reset_date, type,address_id) VALUES (6, 'Sanja', 'Sabiv', '3214567891234', 'sanjaUser', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'usersanja@email.com', '555-000-333', true, '2017-10-01 21:58:58.508-07', 'Dermatologist',2);
INSERT INTO USERS_PHARMACIES (dermatologist_id, pharmacies_id) values (5, 1), (6, 2);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');