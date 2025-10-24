# 🧩 JobFind Backend (Spring Boot)

Backend-сервер для проекта **JobFind** — платформы, которая собирает вакансии из Telegram-каналов, сохраняет их в базе данных и предоставляет REST API для фронтенда на Angular.

---

## 🚀 Overview

JobFind Backend — это серверная часть системы, построенная на **Spring Boot (Java)**.  
Она связывает три слоя проекта:

1. **Python-парсер** — извлекает вакансии из Telegram-каналов и сохраняет их в базу данных (PostgreSQL).
2. **База данных (PostgreSQL)** — хранит информацию о компаниях и вакансиях.
3. **Angular Frontend** — получает данные через REST API, отображает, фильтрует и ищет вакансии.

Все REST эндпоинты доступны по префиксу `/api`.

---

## 🏗️ Technology Stack

| Layer | Technology |
|-------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Build Tool | Maven |
| Database | PostgreSQL (Railway) |
| ORM | Spring Data JPA |
| Server | Embedded Tomcat |
| API | REST + JSON |
| Cross-Origin | Enabled (`@CrossOrigin(origins = "*")`) |

---


## 🌐 API Base URLs
Production:
https://telegramjobbackendjava-production.up.railway.app/api
Local Development:
http://localhost:8080/api
## 📚 API Endpoints
🏢 CompanyController
Base URL: /api/companies
Method	Endpoint	Description
GET	/api/companies	Получить список всех компаний (с пагинацией)
GET	/api/companies/{id}	Получить компанию по ID
GET	/api/companies/name/{name}	Получить компанию по названию
GET	/api/companies/search?query={text}	Поиск компаний по ключевому слову
GET	/api/companies/filter?city={city}	Фильтрация компаний по городу
🔹 Пример запроса
GET /api/companies/filter?city=Almaty&page=0&size=35
🔹 Пример ответа
{
  "content": [
    {
      "id": 1,
      "companyName": "Kaspi.kz",
      "city": "Almaty",
      "industry": "Fintech"
    }
  ],
  "totalPages": 5,
  "totalElements": 150
}

## 💼 VacancyController
Base URL: /api/vacancies
Method	Endpoint	Description
GET	/api/vacancies	Получить список всех вакансий (с пагинацией)
GET	/api/vacancies/{id}	Получить вакансию по ID
GET	/api/vacancies/search?query={text}	Поиск вакансий по ключевому слову
GET	/api/vacancies/filter?location={city}	Фильтрация вакансий по городу
GET	/api/vacancies/company/{companyId}	Получить вакансии конкретной компании
🔹 Пример запроса
GET /api/vacancies/search?query=Java%20Developer&page=0&size=35
🔹 Пример ответа
{
  "content": [
    {
      "id": 42,
      "position": "Java Developer",
      "company": { "id": 1, "companyName": "Kaspi.kz" },
      "salary": "600,000 KZT",
      "location": "Almaty",
      "postedAt": "2025-10-24"
    }
  ],
  "totalPages": 20,
  "totalElements": 700
}
## 🧠 Key Features
✅ Пагинация (page, size) для всех эндпоинтов
🔍 Поиск по ключевым словам
🏙️ Фильтрация по городу / локации
🔗 Связь компаний и вакансий (Company → Vacancy)
🌍 Поддержка CORS для фронтенда
📊 JSON-ответы, удобные для Angular
🧾 Использование Page<T> из Spring Data JPA
🔧 Local Run Guide
🧰 Prerequisites
Java 17+
Maven 3.8+
PostgreSQL
IntelliJ IDEA / VS Code / Eclipse
🏃 Steps
# Клонировать репозиторий
git clone https://github.com/<your-username>/jobfind-backend.git
cd jobfind-backend


# Запустить сервер
mvn spring-boot:run
После запуска сервер будет доступен по адресу:
http://localhost:8080
🌐 Frontend Integration (Angular)
В файле src/environments/environment.ts:
export const environment = {
  production: false,
  apiUrl: 'https://telegramjobbackendjava-production.up.railway.app/api'
};

## Пример вызова в Angular сервисе:
this.http.get(`${environment.apiUrl}/vacancies?page=0&size=35`);
☁️ Deployment
Сервис можно задеплоить на:
Railway.app ✅ (текущий вариант)

🪄 Команда сборки:
mvn clean package
java -jar target/jobfind-backend-0.0.1-SNAPSHOT.jar
