const createNotification = require('./notification.js') 
class Teacher {
    constructor({dni,id,mail,name,notifications ,phoneNum,surname}) {
        this.dni = dni;
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.notifications = notifications ? notifications.map(not => createNotification(not)) : null;
        this.phoneNum = phoneNum;
        this.surname = surname;

    }
}
module.exports = function(teacher) {
    return new Teacher(teacher);
}