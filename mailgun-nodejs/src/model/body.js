const createTeacher = require('./teacher.js');
class Body {
    constructor(data) {
        this.body = createTeacher(data);
    }
}
module.exports = function(data) {
    return new Body(data);
}