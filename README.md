# Marta-Delays-Tracker

MARTA Delays Tracker

A secure, multi-user transit monitoring platform that tracks real-time MARTA rail arrivals and allows users to subscribe to stations for personalized live updates.

Built with Spring Boot, PostgreSQL, Thymeleaf, and the official MARTA Real-Time Rail API.

* Features: 

🔐 Secure user authentication (Spring Security + BCrypt)

🧾 User registration & login

📍 Subscribe / Unsubscribe to rail stations

🚆 Real-time arrival data from MARTA Rail API

🔄 Automatic refresh every 60 seconds

🧠 Server-side filtering of arrivals per subscribed station

🛡 Graceful handling of external API failures

🗄 PostgreSQL-backed persistent storage

🧪 Service-layer unit testing (JUnit)

🏗 Architecture

The application follows a layered architecture:

Controller → Service → Repository → Database

Controllers handle HTTP requests and responses

Services contain business logic and API aggregation

Repositories manage database access via Spring Data JPA

Entities represent domain models (User, Station, Subscription)

Real-time MARTA data is:

Fetched from the external API

Transformed into DTO objects

Filtered server-side based on user subscriptions

Rendered dynamically via Thymeleaf

🛠 Tech Stack

Backend

Java 17+

Spring Boot / Spring Security / Spring Data JPA

Hibernate / RestClient (HTTP integration)

Database: PostgreSQL

Frontend: Thymeleaf (server-rendered views)

Testing: JUnit

Build Tool: Maven



🔐 Environment Configuration

The application uses environment-based configuration.

application.properties (committed)
spring.application.name=marta-delays-tracker
spring.profiles.active=${SPRING_PROFILES_ACTIVE:dev}

marta.api.key=${MARTA_API_KEY}
marta.rail.url=${MARTA_RAIL_URL}

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}


Create:

src/main/resources/application-dev.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/marta-delays-tracker
spring.datasource.username=your_user_name
spring.datasource.password=your_password

marta.api.key=your_marta_api_key
marta.rail.url=https://developerservices.itsmarta.com:18096/itsmarta/railrealtimearrivals/developerservices/traindata



Mac/Linux:

export DB_URL=jdbc:postgresql://localhost:5432/marta-delays-tracker
export DB_USERNAME=you_user_name
export DB_PASSWORD=your_password
export MARTA_API_KEY=your_key
export MARTA_RAIL_URL=https://developerservices.itsmarta.com:18096/itsmarta/railrealtimearrivals/developerservices/traindata

Windows PowerShell:

$env:DB_URL="jdbc:postgresql://localhost:5432/marta-delays-tracker"
$env:DB_USERNAME="your_user_name"
$env:DB_PASSWORD="your_password"
$env:MARTA_API_KEY="your_key"
$env:MARTA_RAIL_URL="https://developerservices.itsmarta.com:18096/itsmarta/railrealtimearrivals/developerservices/traindata"
2️⃣ Run Application
mvn spring-boot:run

Or:

java -jar target/marta-delays-tracker.jar

Application runs at:

http://localhost:8080
🌐 External API Integration

This project integrates with the official MARTA Rail Real-Time Arrivals API.

The system:

Fetches 100+ live arrival records per request

Handles intermittent 500 errors gracefully

Avoids duplicate alerts via state comparison logic

Transforms raw API responses into structured DTOs

🧠 Design Decisions

Server-rendered UI (Thymeleaf) to maintain simplicity and full backend control

No client-side JavaScript frameworks

Layered architecture for separation of concerns

Environment-based configuration for production safety

Graceful fallback when MARTA API is unavailable

📊 Future Improvements

Station-specific delay notification service

Email or SMS alert integration

Caching of last successful API response

Docker containerization

CI/CD deployment pipeline

Role-based admin dashboard

Redis caching layer

👤 Author

Del
Computer Science Graduate

This project is for educational and portfolio purposes.
