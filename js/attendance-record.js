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
  constructor() {
  }

  addAttendee(attendee, callback) {
    callback(attendee);
  }

  fetchAttendees(callback) {
    const quocPh = new Attendee("Quoc PHAN");
    quocPh.presentOnTuesday = true;
    const pierre = new Attendee("Pierre Roset");
    pierre.presentOnThursday = true;
    callback([quocPh, pierre, new Attendee("Gurval Le Bouter")]);
  }
}

class AttendanceRecordRender {
  constructor(attendanceRecordService) {
    this.attendanceRecordService = attendanceRecordService;
    this.attendees = [];
  }
  buildAttendeeRow(attendee) {
    const rowContainer = document.createElement("div");
    rowContainer.classList.add("row-container");
    const namePanel = this.buildNamePanel(attendee.avatar, attendee.name);
    const tuesdayCheck = this.buildCheckPanel(attendee.presentOnTuesday);
    const thursdayCheck = this.buildCheckPanel(attendee.presentOnThursday);

    rowContainer.appendChild(namePanel);
    rowContainer.appendChild(tuesdayCheck);
    rowContainer.appendChild(thursdayCheck);

    return rowContainer;
  }

  buildCheckPanel(isChecked) {
    const checkOpt = document.createElement("div");
    checkOpt.classList.add("check-opt");
    const checkbox = document.createElement("input");
    checkbox.classList.add("switch_1");
    checkbox.type = "checkbox";
    if (isChecked) {
      checkbox.checked="checked";
    } else {
      checkbox.checked="";
    }

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
    this.attendees.push(attendee);
    const listBody = document.getElementById("list-body");
    const attendeeRow = this.buildAttendeeRow(attendee);
    listBody.appendChild(attendeeRow);
  }

  addAttendee(attendee) {
    // Server side
    this.attendanceRecordService.addAttendee(attendee, function(att) {
      this.addAttendeeRow(att);
    }.bind(this));
  }

  onPageLoaded() {
    this.attendanceRecordService.fetchAttendees(function(attendees) {
      attendees.forEach(function(att) {
        this.addAttendeeRow(att);
      }.bind(this));
    }.bind(this));
  }
}

// Global variables
const attendanceRecordService = new AttendanceRecordService();
const attendanceRecordRender = new AttendanceRecordRender(attendanceRecordService);
