# deviceservice

** Build and run**

1. Build the project: mvn clean package (a jar file is formed in the /target folder)
2. Starting the jar: java-jar organizer-1.0.0.jar

** Environment variables**

1. spring.datasource.user - login to the database
2. spring.datasource.PASSWORD - password to the database
3. spring.datasource.URL-database connection
4. SERVER_PORT-port
5. auth.secret-secret key for jwt
6. API: http://localhost:8585/api/getAllDataUser get all data if role is BACK-OFFICE
7. API: http://localhost:8585/api/connect updating users data