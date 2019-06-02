"use strict";
class Attendee {
  constructor(name) {
    this.id = this.generateId();
    this.name = name;
    this.avatar = this.getRandomAvatar();
    this.presentOnTuesday = false;
    this.presentOnThursday = false;
  }

  generateId() {
    const date = new Date();
    return date.getTime();
  }

  getRandomAvatar() {
    const avatarNum = Math.floor(Math.random() * 5) + 1;
    return "images/" + "avatar" + avatarNum + ".png";
  }
}

class AttendanceRecordService {
  constructor(attendanceRecordRender) {
    this.attendanceRecordRender = attendanceRecordRender;
    this.attendees = [];
  }

  addAttendee(attendee) {
    this.attendees.push(attendee);
    this.attendanceRecordRender.addAttendeeRow(attendee);
  }

  fetchAttendees() {
    [new Attendee("Quoc PHAN"),
    new Attendee("Pierre Roset"),
    new Attendee("Gurval Le Bouter")].forEach(function (attendee){
      this.addAttendee(attendee);
    }.bind(this));
  }
}

class AttendanceRecordRender {
  buildAttendeeRow(attendee) {
    const rowContainer = document.createElement("div");
    rowContainer.classList.add("row-container");
    const namePanel = this.buildNamePanel(attendee.avatar, attendee.name);
    const tuesdayCheck = this.buildCheckPanel();
    const thursdayCheck = this.buildCheckPanel();

    rowContainer.appendChild(namePanel);
    rowContainer.appendChild(tuesdayCheck);
    rowContainer.appendChild(thursdayCheck);

    return rowContainer;
  }

  buildCheckPanel() {
    const checkOpt = document.createElement("div");
    checkOpt.classList.add("check-opt");
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox"
    checkOpt.appendChild(checkbox);
    return checkOpt;
  }

  buildNamePanel(avartarSrc, attendeeName) {
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

  addAttendeeRow(attendee) {
    const listBody = document.getElementById("list-body");
    const attendeeRow = this.buildAttendeeRow(attendee);
    listBody.appendChild(attendeeRow);
  }
}

// Global variables
let attendanceRecordService;
function initAttendanceRecordService() {
  const attendanceRecordRender = new AttendanceRecordRender();
  attendanceRecordService = new AttendanceRecordService(attendanceRecordRender);
}
