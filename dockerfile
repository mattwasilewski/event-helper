FROM openjdk:17-jdk-alpine

ENV HTTP_PROXY http://localhost:8080/
ENV HTTPS_PROXY http://localhost:8080/

COPY target/*.jar CodeCool-Project-Grande-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD java -Dhttp.proxyHost=$HTTP_PROXY -Dhttp.proxyPort=$HTTP_PROXY_PORT -Dhttps.proxyHost=$HTTPS_PROXY -Dhttps.proxyPort=$HTTPS_PROXY_PORT -jar CodeCool-Project-Grande-0.0.1-SNAPSHOT.jar

