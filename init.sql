CREATE TABLE IF NOT EXISTS notes (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    note VARCHAR(1000),
    created_at BIGINT,
    updated_at BIGINT
);

CREATE TABLE IF NOT EXISTS users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(225) NOT NULL,
    password VARCHAR(225) NOT NULL,
    created_at BIGINT,
    updated_at BIGINT,
    UNIQUE KEY(email)
);

INSERT INTO users (email, password, created_at, updated_at)
VALUES ('user1@email.com', SHA1('user1password'), 1573384989000, 1573384989000);

INSERT INTO users (email, password, created_at, updated_at)
VALUES ('user2@email.com', SHA1('user2password'), 1573384989000, 1573384989000);

INSERT INTO users (email, password, created_at, updated_at)
VALUES ('user3@email.com', SHA1('user3password'), 1573384989000, 1573384989000);