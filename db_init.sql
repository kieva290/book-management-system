GRANT ALL PRIVILEGES ON bookManagementSystem.* TO 'springuser'@'%' IDENTIFIED BY 'password1234';

USE bookManagementSystem;

CREATE TABLE "role_entity" (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL
);

INSERT INTO "role_entity" (name) VALUES ('USER');