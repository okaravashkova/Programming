CREATE DATABASE IF NOT EXISTS shop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shop;

CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS cart (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NOT NULL,
    product_id INT NOT NULL,
    quantity   INT DEFAULT 1,
    FOREIGN KEY (user_id)    REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE IF NOT EXISTS orders (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total      DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS order_items (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    order_id   INT NOT NULL,
    product_id INT NOT NULL,
    quantity   INT            NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id)   REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

-- Тестовые данные
INSERT IGNORE INTO categories (id, name) VALUES
    (1, 'Сигареты'),
    (2, 'Сигары'),
    (3, 'Табак для трубки');

INSERT IGNORE INTO products (id, name, description, price, category_id) VALUES
    (1, 'Marlboro Red',      'Классические крепкие сигареты',         250.00, 1),
    (2, 'Winston Classic',   'Лёгкие сигареты с фильтром',            200.00, 1),
    (3, 'Parliament Aqua',   'Угольный фильтр, мягкий вкус',          280.00, 1),
    (4, 'Cohiba Siglo VI',   'Кубинская сигара премиум-класса',      1500.00, 2),
    (5, 'Montecristo No.4',  'Классическая кубинская сигара',         900.00, 2),
    (6, 'Dunhill Early Morning', 'Лёгкий трубочный табак',            350.00, 3),
    (7, 'Mac Baren HH Old Dark Fired', 'Трубочный табак огневой сушки', 420.00, 3);
