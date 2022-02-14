-- USER
CREATE TABLE user
(
    id         BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY AUTO_INCREMENT,

    email      varchar(255) NOT NULL UNIQUE,
    password   varchar(255) NOT NULL,
    roles      varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL

) ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_unicode_ci;

-- Author
CREATE TABLE author
(
    id         BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY AUTO_INCREMENT,

    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    location   varchar(1000),
    bio        varchar(10000),
    dob        DATE         NOT NULL,
    active     TINYINT      NOT NULL

) ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_unicode_ci;

-- Book
CREATE TABLE book
(
    id           BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY AUTO_INCREMENT,

    title        varchar(255) NOT NULL,
    genre        varchar(255) NOT NULL,
    description  varchar(10000),
    author_id    BIGINT UNSIGNED NOT NULL,
    borrowed_by  BIGINT UNSIGNED,
    publish_date DATE         NOT NULL,
    available    TINYINT      NOT NULL,

    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (borrowed_by) REFERENCES user (id)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_unicode_ci;

-- Insert Users
INSERT INTO user (email, password, roles, first_name, last_name)
VALUES ('admin@rit.com', '$2a$10$.xaYSfiMMA1mWtZcNe9HjODlWtaOBoq6azpoEy445YFX8alxHPJJC', 'ROLE_ADMIN', 'Ivan',
        'Kolanovic');
INSERT INTO user (email, password, roles, first_name, last_name)
VALUES ('user@rit.com', '$2a$10$.xaYSfiMMA1mWtZcNe9HjODlWtaOBoq6azpoEy445YFX8alxHPJJC', 'ROLE_USER', 'Pero', 'Peric');

-- Insert Author
INSERT INTO author (first_name, last_name, location, bio, dob, active)
VALUES ('H.P', 'Lovecraft', 'Providence, Rhode Island, U.S', null, '1890-08-20', 1);

-- Insert book
INSERT INTO book (title, genre, description, author_id, borrowed_by, publish_date, available)
VALUES ('The Call of Cthulhu', 'Horror',
        'The story''s narrator, Francis Wayland Thurston, recounts his discovery of various notes left behind by his great uncle, George Gammell Angell, a prominent professor of Semitic languages at Brown University in Providence, Rhode Island, who died during the winter of 1926, suspecting eldritch goings-on after being bumped into by a sailor.',
        1, null, '1928-02-10', 1);