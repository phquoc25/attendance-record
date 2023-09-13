mvn clean install
docker build -t phquoc25/attendee-record .
docker run -p8080:8080 phquoc25/attendee-record

