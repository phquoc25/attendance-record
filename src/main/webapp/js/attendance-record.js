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
    const quocPh = new Attendee("Hồ Quốc");
    quocPh.presentOnTuesday = true;
    quocPh.presentOnThursday = true;
    const khanhNg = new Attendee("Văn Khánh");
    khanhNg.presentOnThursday = true;
    const nico = new Attendee("Nicolas");
    nico.presentOnTuesday = true;
    callback([quocPh,
      khanhNg,
      nico,
      new Attendee("Diệu Vi"),
      new Attendee("Mỹ Hân"),
      new Attendee("Hân Nhọn"),
      new Attendee("Minh Quân"),
      new Attendee("Pierre")
    ]);
  }
}

class AttendanceRecordRender {
  constructor(attendanceRecordService) {
    this.attendanceRecordService = attendanceRecordService;
    this.attendees = [];
    this.attendeeNameField = document.getElementById("attendee-name");
    this.addBtn = document.getElementById("add-participant");
    this.deleteBtn = document.getElementById("delete-participant");
    this.setEnable(this.addBtn, false);
    this.setEnable(this.deleteBtn, false);
    this.registerEvents();
  }

  setEnable(element, isEnabled) {
    if (isEnabled) {
      element.classList.remove("disabled");
    } else {
      element.classList.add("disabled");
    }
  }

  registerEvents() {
    this.attendeeNameField.onkeyup = function() {
      if (this.attendeeNameField.value.length > 0) {
        this.setEnable(this.addBtn, true);
        this.setEnable(this.deleteBtn, true);
      } else {
        this.setEnable(this.addBtn, false);
        this.setEnable(this.deleteBtn, false);
      }
    }.bind(this);
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

  onAddAttendee() {
    const attendee = new Attendee(this.attendeeNameField.value);
    this.addAttendee(attendee);
    this.attendeeNameField.value = "";
    this.setEnable(this.addBtn, false);
    this.setEnable(this.deleteBtn, false);
  }
}

// Global variables
let attendanceRecordService;
let attendanceRecordRender;
function initAttendanceRecordComponent() {
  attendanceRecordService = new AttendanceRecordService();
  attendanceRecordRender = new AttendanceRecordRender(attendanceRecordService);
}
