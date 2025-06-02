# ✅ Task Tracker

Приложение позволяет управлять списком задач с помощью веб-интерфейса.

---

## 🚀 Функциональность

- Добавление, редактирование и удаление задач
- Просмотр всех задач в виде списка
- Маркировка задачи как выполненной
- Валидация данных
- Хранение задач в PostgreSQL
- UI с Thymeleaf

---

## 🧰 Используемые технологии

| Категория        | Технология                  |
|------------------|-----------------------------|
| Язык             | Java 24                     |
| Backend          | Spring Boot 3.5.0           |
| ORM              | Spring Data JPA + Hibernate |
| База данных      | PostgreSQL                  |
| Frontend         | Thymeleaf (HTML + шаблоны)  |
| Сборка           | Gradle                      |
| Конфигурация     | YAML + переменные окружения |

## ⚙️ Установка и запуск

### 1. 📥 Клонировать репозиторий

```bash
git clone https://github.com/your-username/spring-task-tracker.git
cd spring-task-tracker
```

### 2. Создать базу данных вручную
Подключитесь к PostgreSQL (например, через psql или DBeaver) и выполните:
```sql
CREATE DATABASE taskdb;
```

### 3. Установить переменные окружения
```
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your_password"
```

