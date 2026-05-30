CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO users (name, email, password, role) 
VALUES ('Administrador', 'admin@support.com', 'admin123', 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE email=email;