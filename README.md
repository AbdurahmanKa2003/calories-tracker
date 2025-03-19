# Calories Tracker Application
#Abdurahman Kaibaliev 
## 📌 Описание
Calories Tracker Application — это REST API сервис для отслеживания дневной нормы калорий и учета съеденных блюд.  
Проект разработан с использованием **Spring Boot**, **Spring Data JPA** и **PostgreSQL**.

## 🛠 Используемые технологии
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Jakarta Validation**
- **JUnit 5**
- **Postman (опционально для тестирования API)**

## 🚀 Функционал
✅ Регистрация и управление пользователями  
✅ Автоматический расчет дневной нормы калорий  
✅ CRUD-операции с блюдами  
✅ Валидация данных  
✅ Обработка ошибок  

## 🏗 Структура проекта
```
📂 src
 ┣ 📂 main
 ┃ ┣ 📂 java/com/daylicode/caloriestrackerapplication
 ┃ ┃ ┣ 📂 controller  # Контроллеры API
 ┃ ┃ ┣ 📂 service     # Бизнес-логика
 ┃ ┃ ┣ 📂 repository  # Репозитории Spring Data JPA
 ┃ ┃ ┣ 📂 model       # Классы сущностей
 ┃ ┃ ┣ 📂 dto         # DTO-классы для передачи данных
 ┃ ┃ ┣ 📂 exception   # Обработчики ошибок
 ┣ 📂 test            # Юнит-тесты
```

## 🏗 Запуск проекта локально

### 1️⃣ Установка зависимостей
Перед запуском убедись, что у тебя установлены:
- **Java 21**
- **PostgreSQL**
- **Maven**

### 2️⃣ Настройки базы данных  
Создай базу данных `calories_tracker_db` в PostgreSQL и пропиши параметры в `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/calories_tracker_db
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 3️⃣ Запуск приложения  
```sh
mvn spring-boot:run
```

## 📬 API Эндпоинты
### 📌 Пользователи (`/api/users`)
| Метод | Эндпоинт        | Описание                  |
|--------|---------------|---------------------------|
| `POST` | `/register`   | Регистрация пользователя |
| `GET`  | `/all`        | Получить всех пользователей |
| `GET`  | `/{id}`       | Получить пользователя по ID |

### 📌 Блюда (`/api/meals`)
| Метод | Эндпоинт        | Описание               |
|--------|---------------|------------------------|
| `POST` | `/add`        | Добавить новое блюдо   |
| `GET`  | `/list`       | Получить список блюд   |
