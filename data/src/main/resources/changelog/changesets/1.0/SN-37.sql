CREATE TABLE relationships.likes
(
    user_id integer NOT NULL REFERENCES entities.users,
    post_id integer NOT NULL REFERENCES entities.posts,
    PRIMARY KEY (user_id, post_id)
);