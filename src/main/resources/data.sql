INSERT INTO Donor(username, firstname, lastname, email, encrypted_Password, enabled, is_active) VALUES
('donoruser', 'John', 'Doe','donor@donor.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', TRUE, TRUE);

INSERT INTO Student(username, firstname, lastname, email, encrypted_Password, school_type, enabled) VALUES
('studentuser', 'Joe', 'Moe','student@student.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 'college', TRUE),
('johntanaka2254', 'john', 'tanaka', 'john.tanaka2254@email.com', '1234', 'university', TRUE),
('shizuekanawa1801', 'shizue', 'kanawa', 'shizue.kanawa1801@email.com', '1234','university', TRUE),
('jackshirogane8206', 'jack', 'shirogane', 'jack.shirogane8206@email.com', '1234','university', TRUE),
('kumikosuzuki8524', 'kumiko', 'suzuki', 'kumiko.suzuki8524@email.com', '1234','university', TRUE),
('jackkanawa7307', 'jack', 'kanawa', 'jack.kanawa7307@email.com', '1234','university', TRUE),
('masamishirogane2313', 'masami', 'shirogane', 'masami.shirogane2313@email.com', '1234','university', TRUE),
('shizueshinomiya3258', 'shizue', 'shinomiya', 'shizue.shinomiya3258@email.com', '1234','university', TRUE),
('jackshinomiya2778', 'jack', 'shinomiya', 'jack.shinomiya2778@email.com', '1234','university', TRUE),
('johnshinomiya226', 'john', 'shinomiya', 'john.shinomiya226@email.com', '1234','university', TRUE),
('masamitanaka6668', 'masami', 'tanaka', 'masami.tanaka6668@email.com', '1234','university', TRUE),
('naokoshirogane5272', 'naoko', 'shirogane', 'naoko.shirogane5272@email.com', '1234','university', TRUE),
('johntanaka586', 'john', 'tanaka', 'john.tanaka586@email.com', '1234','university', TRUE),
('kumikoshirogane9688', 'kumiko', 'shirogane', 'kumiko.shirogane9688@email.com', '1234','university', TRUE),
('masamishinomiya9274', 'masami', 'shinomiya', 'masami.shinomiya9274@email.com', '1234','university', TRUE),
('kumikokanawa9166', 'kumiko', 'kanawa', 'kumiko.kanawa9166@email.com', '1234','university', TRUE),
('naokosuzuki6265', 'naoko', 'suzuki', 'naoko.suzuki6265@email.com', '1234','university', TRUE),
('jackshinomiya81', 'jack', 'shinomiya', 'jack.shinomiya81@email.com', '1234','university', TRUE),
('jackkanawa2218', 'jack', 'kanawa', 'jack.kanawa2218@email.com', '1234','university', TRUE);

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