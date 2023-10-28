-- Creation script for MediLabo Database - OPCR - Project 9 --
-- We verify if the DB is already existing and if yes, we delete this one --
DROP DATABASE IF EXISTS medilabo_patient;
-- We create (again) the DB for the project --
CREATE DATABASE medilabo_patient;
-- We select this new DB --
USE medilabo_patient;
-- Start of the creation of the tables --
-- Table structure for table ‘patient’ --
CREATE TABLE patient (
    id_patient INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender CHAR(1) CHECK (gender IN ('M', 'F')),
    address VARCHAR(255),
    phonenumber VARCHAR(20)
);

-- Insert value into the patient table --
INSERT INTO patient (firstname, lastname, date_of_birth, gender, address, phonenumber) VALUES
('Test', 'TestNone', '1966-12-31', 'F', '1 Brookside St', '100-222-3333'),
('Test', 'TestBorderline', '1945-06-24', 'M', '2 High St', '200-333-4444'),
('Test', 'TestInDanger', '2004-06-18', 'M', '3 Club Road', '300-444-5555'),
('Test', 'TestEarlyOnset', '2002-06-28', 'F', '4 Valley Dr', '400-555-6666');