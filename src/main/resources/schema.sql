CREATE TABLE if not exists book
(
    id     UUID PRIMARY KEY,
    title  TEXT NOT NULL,
    author TEXT NOT NULL
);