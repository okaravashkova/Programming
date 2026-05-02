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
    (2, 'Сигареты с кнопкой'),
    (3, 'Жувачки');

INSERT IGNORE INTO products (id, name, description, price, category_id) VALUES
    (1,  'Сигареты 1',                      'Нормальные сигареты',               120.00, 1),
    (2,  'Сигареты 2',                      'Крутые сигареты',                  130.00, 1),
    (3,  'Сигареты 67',                     'Сигареты для самых маленьких',          115.00, 1),
    (4,  'Сигареты донской табак',          'Донской табак',                  95.00,  1),
    (5,  'Сигареты с кнопкой вкус хот-дог', 'Неописуемо', 180.00, 2),
    (6,  'Бонд с кнопкой',                 'Отлично с сидром',  170.00, 2),
    (7,  'Кнопка без сигареты',             'Просто кнопка',                   5.00,  2),
    (8,  'Жувачка за 1 рубль с наклейкой',  'Наклейка в подарок',              10.00,  3);
