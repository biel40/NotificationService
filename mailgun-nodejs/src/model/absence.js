class Absence {
    constructor(date, id, subject, time) {
        this.date = date;
        this.id = id;
        this.subject = subject;
        this.time = time;
    }
}
module.exports = function(abse) {
    return new Absence(abse);
}