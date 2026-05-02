# Интернет-магазин — Веб-приложение на Java

## Описание проекта

Веб-приложение интернет-магазина на Java с использованием базы данных MySQL и архитектуры MVC.
Пользователи могут просматривать товары по категориям, добавлять их в корзину и оформлять заказы.

## Технологии

- Java 17
- MySQL 8+
- Maven
- Jakarta Servlet API 6.0
- JSP + JSTL 3.0
- Jetty 11 (для запуска через Maven)
- Apache Tomcat 10+ (для деплоя WAR)

## Структура проекта

```
MIPT_project/
├── Java/shop/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/cigshop/
│   │       │   ├── dao/            # Работа с базой данных
│   │       │   │   ├── BaseDao.java
│   │       │   │   ├── UserDao.java
│   │       │   │   ├── ProductDao.java
│   │       │   │   ├── CategoryDao.java
│   │       │   │   ├── CartDao.java
│   │       │   │   └── OrderDao.java
│   │       │   ├── model/          # Модели данных (бины)
│   │       │   │   ├── User.java
│   │       │   │   ├── Product.java
│   │       │   │   ├── Category.java
│   │       │   │   ├── CartItem.java
│   │       │   │   ├── Order.java
│   │       │   │   └── OrderItem.java
│   │       │   └── servlet/        # Контроллеры (сервлеты)
│   │       │       ├── RegisterServlet.java
│   │       │       ├── LoginServlet.java
│   │       │       ├── LogoutServlet.java
│   │       │       ├── ProductServlet.java
│   │       │       ├── ProductDetailServlet.java
│   │       │       ├── CategoryServlet.java
│   │       │       ├── CartServlet.java
│   │       │       └── OrderServlet.java
│   │       ├── resources/
│   │       │   └── init.sql        # SQL-скрипт инициализации БД
│   │       └── webapp/
│   │           ├── WEB-INF/
│   │           │   └── web.xml     # Конфигурация веб-приложения
│   │           ├── css/
│   │           │   └── style.css
│   │           ├── index.jsp
│   │           ├── login.jsp
│   │           ├── register.jsp
│   │           ├── products.jsp
│   │           ├── product.jsp
│   │           ├── categories.jsp
│   │           ├── cart.jsp
│   │           └── orders.jsp
│   └── pom.xml                     # Конфигурация Maven
└── README.md
```

## Требования

- JDK 17 или выше
- Maven 3.6+
- MySQL 8.0+

## Установка и запуск

### 1. Настройка базы данных

Убедитесь, что MySQL запущен, затем выполните:

```bash
mysql -u shopuser -pshoppass < Java/shop/src/main/resources/init.sql
```

Если пользователь `shopuser` ещё не создан, сначала выполните в MySQL:

```sql
CREATE USER 'shopuser'@'localhost' IDENTIFIED BY 'shoppass';
GRANT ALL PRIVILEGES ON shop.* TO 'shopuser'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Настройка подключения к БД

Откройте `Java/shop/src/main/java/com/cigshop/dao/BaseDao.java` и при необходимости измените параметры:

```java
return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/shop?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
    "shopuser",
    "shoppass"
);
```

### 3. Сборка проекта

```bash
cd Java/shop
mvn clean package
```

WAR-файл появится в `Java/shop/target/shop.war`.

### 4. Запуск через Apache Tomcat 10+

1. Скопируйте WAR-файл в директорию `webapps` вашего Tomcat
2. Запустите Tomcat
3. Откройте браузер: `http://localhost:8080/shop`

## Функционал

- Регистрация и вход в аккаунт
- Просмотр каталога товаров
- Просмотр категорий и фильтрация товаров по категории
- Просмотр карточки товара
- Добавление товара в корзину
- Просмотр корзины и удаление товаров
- Оформление заказа
- Просмотр истории заказов

## Структура базы данных

| Таблица | Описание |
|---|---|
| `users` | Пользователи (логин, пароль, email) |
| `categories` | Категории товаров |
| `products` | Товары (название, описание, цена, категория) |
| `cart` | Корзина пользователя |
| `orders` | Заказы |
| `order_items` | Состав заказов |

## Тестовые данные

В `init.sql` уже включены тестовые данные: 3 категории и 8 товаров.

**Категории:** Сигареты, Сигареты с кнопкой, Жувачки
