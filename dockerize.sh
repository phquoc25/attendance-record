mvn clean install;
docker build -t attendee-record . ;
docker run -p8080:8080 attendee-record;

