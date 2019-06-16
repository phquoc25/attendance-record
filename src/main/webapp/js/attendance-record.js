"use strict";

class Attendee {
    constructor(name, id, avatar, presentOnTuesday, presentOnThursday) {
        this.name = name;
        this.id = id ? id : Attendee.generateId();
        this.avatar = avatar ? avatar : Attendee.getRandomAvatar();
        this.presentOnTuesday = !!presentOnTuesday;
        this.presentOnThursday = !!presentOnThursday;
    }

    static generateId() {
        const date = new Date();
        return date.getTime();
    }

    static getRandomAvatar() {
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
        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const attendees = [];
                JSON.parse(this.responseText)
                    .forEach(function (att) {
                        attendees.push(new Attendee(att.name, att.id, att.avatar, att.presentOnTuesday, att.presentOnThursday));
                    });
                callback(attendees);
            }
        };
        xhttp.open("GET", "attendees", true);
        xhttp.send();
    }

    saveAttendees(attendees, successCallback, errorCallback) {
        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4) {
                if (this.status === 200) {
                    successCallback();
                } else {
                    errorCallback();
                }
            }
        };
        xhttp.open("PUT", "attendees", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(attendees));
    }
}

class AttendanceRecordRender {
    constructor(attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
        this.attendees = [];
        this.attendeeNameField = document.getElementById("attendee-name");
        this.addBtn = document.getElementById("add-participant");
        this.deleteBtn = document.getElementById("delete-participant");
        this.saveBtn = document.getElementById("save-participants");
        this.tuesdayStatus = document.getElementById("tuesday-status-img");
        this.thursdayStatus = document.getElementById("thursday-status-img");
        this.listBody = document.getElementById("list-body");
        this.attendeeRowMap = {};
        AttendanceRecordRender.setEnable(this.addBtn, false);
        AttendanceRecordRender.setEnable(this.deleteBtn, false);
        this.registerEvents();
    }

    static get HAPPY_CLS() {
        return "happy";
    }

    static get UNHAPPY_CLS() {
        return "unhappy";
    }

    static setEnable(element, isEnabled) {
        if (isEnabled) {
            element.classList.remove("disabled");
        } else {
            element.classList.add("disabled");
        }
    }

    registerEvents() {
        const that = this;
        this.attendeeNameField.oninput = () => {
            if (that.attendeeNameField.value.trim().length > 0) {
                AttendanceRecordRender.setEnable(that.addBtn, true);
                AttendanceRecordRender.setEnable(that.deleteBtn, true);
            } else {
                AttendanceRecordRender.setEnable(that.addBtn, false);
                AttendanceRecordRender.setEnable(that.deleteBtn, false);
            }
        };

        this.attendeeNameField.onkeydown = (event) => {
            if (event.code === "Enter" && that.attendeeNameField.value !== "") {
                that.onAddAttendee();
            }
        };

        this.addBtn.onclick = () => {
            that.onAddAttendee();
        };

        this.saveBtn.onclick = () => {
            AttendanceRecordRender.setEnable(that.saveBtn, false);
            that.attendanceRecordService.saveAttendees(that.attendees,
                    function () {
                        window.alert("Attendees have been saved successfully");
                        AttendanceRecordRender.setEnable(that.saveBtn, true);
                    },
                    function () {
                        window.alert("Attendees save failure!!!!");
                        AttendanceRecordRender.setEnable(that.saveBtn, true);
                    });
        };

        this.deleteBtn.onclick = () => {
            that.onDeleteAttendeeClicked();
        };
    }

    buildAttendeeRow(attendee) {
        const rowContainer = document.createElement("div");
        rowContainer.classList.add("row-container");
        const namePanel = this.buildNamePanel(attendee.avatar, attendee.name);
        const tuesdayCheck = this.buildCheckPanel(attendee.presentOnTuesday);
        const that = this;
        tuesdayCheck.onclick = () => {
            attendee.presentOnTuesday = !attendee.presentOnTuesday;
            that.refreshTuesdayStatus();
        };
        const thursdayCheck = this.buildCheckPanel(attendee.presentOnThursday);
        thursdayCheck.onclick = () => {
            attendee.presentOnThursday = !attendee.presentOnThursday;
            that.refreshThursdayStatus();
        };

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
            checkbox.checked = "checked";
        } else {
            checkbox.checked = "";
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

        const that = this;
        namePanel.onclick = () => {
            that.setAttendeeNameFieldValue(attendeeName);
        };

        return namePanel;
    }

    addAttendeeRow(attendee) {
        this.attendees.push(attendee);
        const attendeeRow = this.buildAttendeeRow(attendee);
        this.attendeeRowMap[attendee.name] = attendeeRow;
        this.listBody.appendChild(attendeeRow);
    }

    addAttendee(attendee) {
        // Server side
        const that = this;
        this.attendanceRecordService.addAttendee(attendee, function (att) {
            that.addAttendeeRow(att);
        });
    }

    onPageLoaded() {
        const that = this;
        this.attendanceRecordService.fetchAttendees(function (attendees) {
            attendees.forEach(function (att) {
                that.addAttendeeRow(att);
            });
            that.refreshTuesdayStatus();
            that.refreshThursdayStatus();
        });
    }

    onAddAttendee() {
        const name = this.attendeeNameField.value.trim();
        if (this.isNameValid(name)) {
            const attendee = new Attendee(name);
            this.addAttendee(attendee);
            this.setAttendeeNameFieldValue("");
        } else {
            window.alert("The name " + name + " is already exist. Please input a new name.");
        }
    }

    refreshTuesdayStatus() {
        if (this.attendees.filter(att => att.presentOnTuesday).length > 3) {
            this.tuesdayStatus.classList.add(AttendanceRecordRender.HAPPY_CLS);
            this.tuesdayStatus.classList.remove(AttendanceRecordRender.UNHAPPY_CLS);
        } else {
            this.tuesdayStatus.classList.remove(AttendanceRecordRender.HAPPY_CLS);
            this.tuesdayStatus.classList.add(AttendanceRecordRender.UNHAPPY_CLS);
        }
    }

    refreshThursdayStatus() {
        if (this.attendees.filter(att => att.presentOnThursday).length > 3) {
            this.thursdayStatus.classList.add(AttendanceRecordRender.HAPPY_CLS);
            this.thursdayStatus.classList.remove(AttendanceRecordRender.UNHAPPY_CLS);
        } else {
            this.thursdayStatus.classList.remove(AttendanceRecordRender.HAPPY_CLS);
            this.thursdayStatus.classList.add(AttendanceRecordRender.UNHAPPY_CLS);
        }
    }

    onDeleteAttendeeClicked() {
        const attendeeName = this.attendeeNameField.value;
        const attendeeRow = this.attendeeRowMap[attendeeName];
        if (!attendeeRow) {
            window.alert("The attendee with name " + attendeeName + " is not found. Please select on the list.")
        } else {
            this.listBody.removeChild(attendeeRow);
            this.setAttendeeNameFieldValue("");
        }
    }

    setAttendeeNameFieldValue(newVal) {
        this.attendeeNameField.value = newVal;
        this.attendeeNameField.oninput();
    }

    isNameValid(newName) {
        return this.attendees.filter(att => att.name === newName).length === 0;
    }
}

// Global variables
let attendanceRecordService;
let attendanceRecordRender;

function initAttendanceRecordComponent() {
    attendanceRecordService = new AttendanceRecordService();
    attendanceRecordRender = new AttendanceRecordRender(attendanceRecordService);
}
