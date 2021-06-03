CREATE TABLE commons.complaint_types
(
    complaint_type_id integer PRIMARY KEY,
    name              character varying(32) NOT NULL UNIQUE,
    description       character varying(100)
);

INSERT INTO commons.complaint_types
VALUES (1, 'FRAUD', 'Мошенничество'),
       (2, 'SPAM', 'Спам'),
       (3, 'INSULT', 'Оскорбление'),
       (4, 'CD', 'Детская порнография');

CREATE TABLE relationships.complaints_on_posts
(
    complaint_id   serial PRIMARY KEY,
    user_id        integer NOT NULL REFERENCES entities.users,
    post_id        integer NOT NULL REFERENCES entities.posts,
    complaint_type integer NOT NULL REFERENCES commons.complaint_types
);


