FROM maven:latest AS build
USER root
COPY src /home/app/src
ADD pom.xml /home/app
RUN apt-get update && apt-get install -y wget &&\
    wget https://www.mirea.ru/upload/medialibrary/80f/MIREA_Gerb_Colour.png -P /home/app/src/main/resources/static
RUN mvn -f /home/app/pom.xml clean install -DskipTests

FROM openjdk:17-alpine3.14
LABEL maintainer="Ladygin Sergei Alekseevich, IKBO-01-19"
ENV DB_PORT=15432
CMD mkdir /usr/local/lib/app
WORKDIR /usr/local/lib/app
COPY --from=build /home/app/target/docker-spring-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app/demo.jar"]
ONBUILD RUN echo "Ladygin Sergei Alekseevich"