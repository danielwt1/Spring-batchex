DROP TABLE posts IF EXISTS;

CREATE TABLE posts(
                        id BIGINT IDENTITY NOT NULL PRIMARY KEY,
                        userId INTEGER,
                        title VARCHAR(200),
                        body VARCHAR(500),

);