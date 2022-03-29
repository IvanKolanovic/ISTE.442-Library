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

    name varchar(255) NOT NULL,
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
