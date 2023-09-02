FROM openjdk:17
WORKDIR /attendance-app
ENV HOME /attendance-app
EXPOSE 8080
COPY target/attendance-record-0.0.1-SNAPSHOT.jar attendance-record.jar
COPY target/classes/attendees.json $HOME/attendees.json
CMD java -jar /attendance-app/attendance-record.jar