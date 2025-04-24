CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        username VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
        id SERIAL PRIMARY KEY,
        title VARCHAR(50) NOT NULL
);

INSERT INTO roles (title)
VALUES
    ('Пользователь'),
    ('Автор'),
    ('Администратор');

CREATE TABLE user_roles (
        user_id BIGINT NOT NULL,
        role_id BIGINT NOT NULL,
        PRIMARY KEY (user_id, role_id),
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
        FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);
