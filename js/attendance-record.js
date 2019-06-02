"use strict";
class Attendee {
  constructor(name) {
    this.id = this.generateId();
    this.name = name;
    this.avatar = "images/avatar2.png";
    this.presentOnTuesday = false;
    this.presentOnThursday = false;
  }

  generateId() {
    const date = new Date();
    return date.getTime();
  }
}

class AttendanceRecord {
  constructor() {
    this.attendees = [];
  }

  addAttendee(attendee) {
    this.attendees.push(attendee);
  }
}

function buildAttendeeRow(attendee) {
  const rowContainer = document.createElement("div");
  rowContainer.classList.add("row-container");
  const namePanel = buildNamePanel(attendee.avatar, attendee.name);
  const tuesdayCheck = buildCheckPanel();
  const thursdayCheck = buildCheckPanel();

  rowContainer.appendChild(namePanel);
  rowContainer.appendChild(tuesdayCheck);
  rowContainer.appendChild(thursdayCheck);

  return rowContainer;
}

function buildCheckPanel() {
  const checkOpt = document.createElement("div");
  checkOpt.classList.add("check-opt");
  const checkbox = document.createElement("input");
  checkbox.type = "checkbox"
  checkOpt.appendChild(checkbox);
  return checkOpt;
}

function buildNamePanel(avartarSrc, attendeeName) {
  const namePanel = document.createElement("div");
  namePanel.classList.add("name-panel");

  const avatar = document.createElement("img");
  avatar.classList.add("avatar");
  avatar.src = avartarSrc;

  const name = document.createElement("div");
  name.classList.add("name");
  name.innerHTML = attendeeName;

  namePanel.appendChild(avatar);
  namePanel.appendChild(name);

  return namePanel;
}

function onAddNewClicked() {
  const attendee = new Attendee('unname');
  addAttendeeRow(attendee);
  addAttendeeToCache(attendee);
}

function addAttendeeRow(attendee) {
  const listBody = document.getElementById("list-body");
  const attendeeRow = buildAttendeeRow(attendee);
  listBody.appendChild(attendeeRow);
}

function addAttendeeToCache(attendee) {
  let attendanceRecord = localStorage.getItem("attendanceRecord");
  if (attendanceRecord) {
    const attendees = JSON.parse(attendanceRecord).attendees;
    attendanceRecord = new AttendanceRecord();
    attendanceRecord.attendees = attendees;
    attendanceRecord.addAttendee(attendee);
    localStorage.setItem("attendanceRecord", JSON.stringify(attendanceRecord));
  } else {
    attendanceRecord = new AttendanceRecord();
    attendanceRecord.addAttendee(attendee);
    localStorage.setItem("attendanceRecord", JSON.stringify(attendanceRecord));
  }
}

let attendanceRecord = localStorage.getItem("attendanceRecord");
if (attendanceRecord) {
  JSON.parse(attendanceRecord).attendees.forEach(function(attendee) {
    addAttendeeRow(attendee);
  });
}
