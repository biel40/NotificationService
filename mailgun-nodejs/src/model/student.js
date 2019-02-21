const createAbsence = require('./absence.js');
class Student {
    constructor({absences, dni, id, name, surname}) {
        this.absences = absences ? absences.map(absence => createAbsence(absence)) : [];
        this.dni = dni;
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
module.exports = function(student) {
    return new Student(student);
}