# Add or update new attendee
curl -d '{"name":"Quoc Jr", "tuesdayOn":"true", "thursdayOn": "false"}' -H "Content-Type: application/json" -X POST http://localhost:8080/attendees

# get all attendees
curl http://localhost:8080/attendees