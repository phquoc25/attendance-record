# Add or update new attendee
curl -v -d '{"name":"Quoc Jr", "tuesdayOn":"true", "thursdayOn": "false"}' -H "Content-Type: application/json" -X POST http://localhost:8080/attendees

# get all attendees
curl -v http://localhost:8080/attendees

# remove attendee
curl -v -X DELETE http://localhost:8080/admin/attendees/{id}

# Add new attendee v2
curl -v -d '{"name":"Quoc Jr", "tuesdayOn":"true", "thursdayOn": "false"}' -H "Content-Type: application/json" http://localhost:8080/v2/attendees