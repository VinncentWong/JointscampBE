FROM openjdk:17-jdk-alpine

#Set Environment
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://jointscamp_db:5432/jointscamp
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=jointscamp
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_OPEN_IN_VIEW=false
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect

# not a good practice to set a hardcoded secret key
ENV SECRET_KEY=yoursecretkey123X12300ASFALKSDxmvcasdij

WORKDIR /app
COPY . .
WORKDIR /.mvn
RUN ls -a
WORKDIR /app
RUN ./mvnw dependency:go-offline
ENTRYPOINT ["./mvnw", "spring-boot:run"]