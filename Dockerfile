FROM java:8
WORKDIR /attendance-app
ENV HOME /attendance-app
COPY target/attendance-record.war attendance-record.war
COPY target/classes/attendees.json $HOME/attendees.json
CMD java -jar /attendance-app/attendance-record.war