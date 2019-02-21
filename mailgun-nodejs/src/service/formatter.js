class Formatter {
    constructor(data) {
        this.data = data;
    }

    toHtml() {
        return "";
    }

}

module.exports = function(obj) {
    return new Formatter(obj);
}