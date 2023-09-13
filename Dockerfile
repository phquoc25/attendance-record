FROM openjdk:17
WORKDIR /attendance-app
ENV HOME /attendance-app
EXPOSE 8080
COPY attendance-record-ws/target/attendance-record-ws-0.0.1-SNAPSHOT.jar attendance-record.jar
CMD java -jar /attendance-app/attendance-record.jar