# Spring Movie Theatre App

Tема: "Система Кинотеатр" 

ТЗ: Интернет витрина маленького кинотеатра с одним залом. В нем есть расписание показа фильмов на все 7 дней недели 
с 9:00 до 22:00 (начало последнего фильма). Незарегистрированный пользователь может видеть: расписание, свободные
места в зале, возможность зарегистрироваться. Зарегистрированный пользователь должен быть в состоянии выкупить 
билет на выбранное место. Администратор может: внести в расписание новый фильм, отменить фильм,
просматривать посещаемость зала.
Уровни доступа: аноним, USER, ADMIN.

## Технологии
- DB - MySql 8.x
- Java version 8 or higher
- Maven

## Установка и запуск проекта

1. Clone project with 'git clone' command from command line
2. Install plugin for Lombok library in your IDEA
3. Correct movie session start_at column in movie_session table in src/java/resources/data.sql (insert dates from current date or higher)
4. Update DB login and password in application.properties from src/main/resources folder
5. Create schema 'movie_theatre_spring'
6. Start mysql service with 'service mysql start' (http://www.mysqltutorial.org/mysql-adminsitration/start-mysql/)
7. Go to project root ../SpringMovieTheatreApp directory and run in terminal command 'mvn spring-boot:run -f pom.xml'
8. Go to link localhost:8080 and have fan
9. Use username:admin password:admin to check up admin functional, username:test password:test for user functional (or create your own account)
10. Use Ctrl+C command in command line to force quit and kill all app process
11. Stop mysql service with 'service mysql stop' or 'mysql.service stop' (http://www.mysqltutorial.org/mysql-adminsitration/stop-mysql/)

## Доступный функционал

- #### Уровень доступа - любой:

1.  Стартовая страница
2.  Расписание сеансов (уникальное view для администратора)
3.  Просмотр заполненности зала на определенный сеанс
4.  Вход в систему
5.  Регистрация
6.  Смена языка

- #### Уровень доступа - любой пользователь вошедший в систему:

7.  Возможномть выйти из системы

- #### Уровень доступа - USER:

8.  Список актуальных купленных сеансов
9.  При просмотре зала возможность купить билеты

- #### Уровень доступа - ADMIN:

10.  Просмотр зала без интерактива
11.  Возможность добавить/удалить новый фильм в систему
12.  Возможность прикрепить/открепить фильм к определенному дню
13.  Возможность добавить/удалить новый сеанс для фильма
