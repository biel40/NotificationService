const createTeacher = require('./model/teacher.js');
class EmailContainer {
    constructor(data) {
        this.teacher = createTeacher(data);
    }

}
module.exports = function(data) {
    return new EmailContainer(data);
}