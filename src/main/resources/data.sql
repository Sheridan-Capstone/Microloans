INSERT INTO Donor(username, firstname, lastname, email, encrypted_Password, enabled, is_active) VALUES
('donoruser', 'John', 'Doe','donor@donor.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', TRUE, TRUE);


INSERT INTO Student(username, firstname, lastname, email, encrypted_Password, school_type, enabled, high_school_id, high_school_name, post_secondary_name, field_of_interest, field_of_interest_id, story, gpa, city, approval) VALUES
('studentuser', 'Joe', 'Moe','student@student.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 'college', TRUE, 1, 'Glenforest', 'Sheridan College', 'Art', 1, 'Came from poor family', 4.0, 'Mississauga', 1),
('johntanaka2254', 'john', 'tanaka', 'john.tanaka2254@email.com', '1234', 'university', TRUE, 1, 'Glenforest', 'University of Toronto', 'Art', 1, 'Came from poor family', 2.5, 'Mississauga', 1),
('shizuekanawa1801', 'shizue', 'kanawa', 'shizue.kanawa1801@email.com', '1234','university', TRUE, 1, 'Glenforest', 'McMaster Univeristy', 'Art', 1, 'Came from poor family', 3.0, 'Mississauga', 0),
('jackshirogane8206', 'jack', 'shirogane', 'jack.shirogane8206@email.com', '1234','university', TRUE, 1, 'Glenforest','Sheridan College', 'Art', 1, 'Came from poor family', 4.0, 'Mississauga', 0),
('kumikosuzuki8524', 'kumiko', 'suzuki', 'kumiko.suzuki8524@email.com', '1234','university', TRUE, 1, 'Glenforest', 'University of Toronto', 'Art', 1, 'Came from poor family', 3.2, 'Mississauga', 0),
('jackkanawa7307', 'jack', 'kanawa', 'jack.kanawa7307@email.com', '1234','university', TRUE, 1, 'Glenforest', 'McMaster University', 'Art', 1, 'Came from poor family', 3.4, 'Mississauga', 2),
('masamishirogane2313', 'masami', 'shirogane', 'masami.shirogane2313@email.com', '1234','university', TRUE, 1, 'Glenforest', 'Sheridan College', 'Art', 1, 'Came from poor family', 2.9, 'Mississauga', 2),
('shizueshinomiya3258', 'shizue', 'shinomiya', 'shizue.shinomiya3258@email.com', '1234','university', TRUE, 1, 'Glenforest', 'University of Toronto', 'Art', 1, 'Came from poor family', 4.0, 'Brampton', 2),
('jackshinomiya2778', 'jack', 'shinomiya', 'jack.shinomiya2778@email.com', '1234','university', TRUE, 1, 'Glenforest', 'McMaster Univeristy', 'Art', 1, 'Came from poor family', 3.0, 'Brampton', 1),
('johnshinomiya226', 'john', 'shinomiya', 'john.shinomiya226@email.com', '1234','university', TRUE, 1, 'Glenforest', 'Sheridan College', 'Art', 1, 'Came from poor family', 3.4, 'Brampton', 0),
('masamitanaka6668', 'masami', 'tanaka', 'masami.tanaka6668@email.com', '1234','university', TRUE, 1, 'Glenforest', 'University of Toronto','Art', 1, 'Came from poor family', 3.6, 'Brampton', 2),
('naokoshirogane5272', 'naoko', 'shirogane', 'naoko.shirogane5272@email.com', '1234','university', TRUE, 1, 'Glenforest', 'McMaster Univeristy', 'Art', 1, 'Came from poor family', 4.0, 'Brampton', 1),
('johntanaka586', 'john', 'tanaka', 'john.tanaka586@email.com', '1234','university', TRUE, 1, 'Glenforest', 'Sheridan College', 'Art', 1, 'Came from poor family', 4.0, 'Brampton', 1),
('kumikoshirogane9688', 'kumiko', 'shirogane', 'kumiko.shirogane9688@email.com', '1234','university', TRUE, 1, 'Glenforest', 'University of Toronto', 'Art', 1, 'Came from poor family', 4.0, 'Mississauga', 1),
('masamishinomiya9274', 'masami', 'shinomiya', 'masami.shinomiya9274@email.com', '1234','university', TRUE, 1, 'Glenforest', 'McMaster Univeristy', 'Art', 1, 'Came from poor family', 2.7, 'Mississauga', 1),
('kumikokanawa9166', 'kumiko', 'kanawa', 'kumiko.kanawa9166@email.com', '1234','university', TRUE, 1, 'Glenforest', 'Sheridan College', 'Art', 1, 'Came from poor family', 2.8, 'Mississauga', 1),
('naokosuzuki6265', 'naoko', 'suzuki', 'naoko.suzuki6265@email.com', '1234','university', TRUE, 1, 'Glenforest', 'University of Toronto', 'Art', 1, 'Came from poor family', 4.0, 'Mississauga', 1),
('jackshinomiya81', 'jack', 'shinomiya', 'jack.shinomiya81@email.com', '1234','university', TRUE, 1, 'Glenforest', 'McMaster Univeristy', 'Art', 1, 'Came from poor family', 4.0, 'Brampton', 1),
('jackkanawa2218', 'jack', 'kanawa', 'jack.kanawa2218@email.com', '1234','university', TRUE, 1, 'Glenforest', 'Sheridan College', 'Art', 1, 'Came from poor family', 4.0, 'Brampton', 1);


INSERT INTO Admin(username, firstname, lastname, email, encrypted_Password, enabled) VALUES
('adminuser', 'Jack', 'Poe','admin@admin.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', true);

INSERT INTO Donation(amount, date, donor_email, student_email) VALUES
(145.00,'2021-09-10 21:49:27.000000','donor@donor.com','student@student.com');

INSERT INTO Role(rolename) VALUES 
('ROLE_DONOR'),
('ROLE_STUDENT'),
('ROLE_ADMIN');

INSERT INTO Field_Of_Interest(name) VALUES 
('Art');

INSERT INTO High_School(name) VALUES 
('Glenforest'),
('Applewood'),
('Gordon Greydon'),
('Woodlands');

INSERT INTO Message(receiver, sender, body, approval, archive_status) VALUES 
('student@student.com','donor@donar.com','hello Student',1,0),
('john.tanaka2254@email.com','donor@donor.com','sup john', 1,1),
('shizue.kanawa1801@email.com','donor@donor.com','hello there', 1,1),
('jack.shirogane8206@email.com','donor@donor.com','hahahaha', 0,0),
('kumiko.suzuki8524@email.com','donor@donor.com','hahahahahhehehehe',2,1),
('donor@donor.com','jack.kanawa7307@email.com','hello mr.donor',1,0);

INSERT INTO Donor_Role_List VALUES 
(1, 1);

INSERT INTO Student_Role_List VALUES 
(1, 2);

INSERT INTO Admin_Role_List VALUES 
(1, 3);


