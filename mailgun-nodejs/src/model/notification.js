const createStudent = require('./student.js');
class Notification {
    constructor({date, id, itWasSent, students, time}) {
        this.date = date;
        this.id = id;
        this.itWasSent = itWasSent;
        this.students = students ? students.map(stud => createStudent(stud)) : [];
        this.time = time;
    }
}
module.exports = function(notification) {
    return new Notification(notification);
} 