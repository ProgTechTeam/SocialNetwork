CREATE SCHEMA commons;

CREATE TABLE commons.roles
(
    role_id integer PRIMARY KEY,
    name    character varying(32) NOT NULL UNIQUE
);

INSERT INTO commons.roles
VALUES (1, 'USER'),
       (2, 'MODERATOR');

CREATE TABLE commons.chat_types
(
    chat_type_id integer PRIMARY KEY,
    name         character varying(32) NOT NULL UNIQUE
);

INSERT INTO commons.chat_types
VALUES (1, 'PRIVATE'),
       (2, 'GROUP');



CREATE SCHEMA entities;

CREATE TABLE entities.users
(
    user_id    serial PRIMARY KEY,
    first_name character varying(32) NOT NULL,
    last_name  character varying(32) NOT NULL
);

CREATE TABLE entities.accounts
(
    account_owner_id integer               NOT NULL REFERENCES entities.users,
    email            character varying(32) NOT NULL UNIQUE,
    is_blocked       boolean DEFAULT FALSE,
    is_deleted       boolean DEFAULT FALSE,
    password         character(60)         NOT NULL,
    role             integer               NOT NULL REFERENCES commons.roles
);

CREATE TABLE entities.chats
(
    chat_id                    serial PRIMARY KEY,
    chat_type                  integer NOT NULL REFERENCES commons.chat_types,
    name                       character varying(32),
    first_participant_user_id  integer REFERENCES entities.users,
    second_participant_user_id integer REFERENCES entities.users
);

CREATE TABLE entities.communities
(
    community_id serial PRIMARY KEY,
    name         character varying(32) NOT NULL UNIQUE,
    owner_id     integer               NOT NULL REFERENCES entities.users
);

CREATE TABLE entities.messages
(
    message_id serial PRIMARY KEY,
    created_at timestamp without time zone NOT NULL DEFAULT LOCALTIMESTAMP,
    payload    character varying(255)      NOT NULL,
    author_id  integer                     NOT NULL REFERENCES entities.users,
    chat_id    integer                     NOT NULL REFERENCES entities.chats
);

CREATE TABLE entities.posts
(
    post_id    serial PRIMARY KEY,
    created_at timestamp without time zone NOT NULL DEFAULT LOCALTIMESTAMP,
    payload    character varying(255)      NOT NULL,
    author_id  integer                     NOT NULL REFERENCES entities.users
);



CREATE SCHEMA relationships;

CREATE TABLE relationships.communities_posts
(
    community_id integer NOT NULL REFERENCES entities.communities,
    post_id      integer NOT NULL REFERENCES entities.posts,
    PRIMARY KEY (community_id, post_id)
);

CREATE TABLE relationships.subscriptions_on_communities
(
    user_id      integer NOT NULL REFERENCES entities.users,
    community_id integer NOT NULL REFERENCES entities.communities,
    PRIMARY KEY (user_id, community_id)
);

CREATE TABLE relationships.subscriptions_on_users
(
    subscriber     integer NOT NULL REFERENCES entities.users,
    user_to_follow integer NOT NULL REFERENCES entities.users,
    PRIMARY KEY (subscriber, user_to_follow)
);

CREATE TABLE relationships.users_group_chats
(
    user_id integer NOT NULL REFERENCES entities.users,
    chat_id integer NOT NULL REFERENCES entities.chats,
    PRIMARY KEY (user_id, chat_id)
);

CREATE TABLE relationships.users_posts
(
    user_id integer NOT NULL REFERENCES entities.users,
    post_id integer NOT NULL REFERENCES entities.posts,
    PRIMARY KEY (user_id, post_id)
);
