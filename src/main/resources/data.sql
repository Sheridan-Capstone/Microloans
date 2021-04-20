INSERT INTO Donor(username, firstname, lastname, email, encrypted_Password, enabled) VALUES
('donoruser', 'John', 'Doe','donor@donor.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', true);

INSERT INTO Student(username, firstname, lastname, email, encrypted_Password, enabled) VALUES
('studentuser', 'Joe', 'Moe','student@student.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', true);

INSERT INTO Admin(username, firstname, lastname, email, encrypted_Password, enabled) VALUES
('adminuser', 'Jack', 'Poe','admin@admin.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', true);


INSERT INTO Role(rolename) VALUES 
('ROLE_DONOR'),
('ROLE_STUDENT'),
('ROLE_ADMIN');

INSERT INTO Donor_Role_List VALUES 
(1, 1);

INSERT INTO Student_Role_List VALUES 
(1, 2);

INSERT INTO Admin_Role_List VALUES 
(1, 3);