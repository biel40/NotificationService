const {check, success} = require('./configs/mailgun.config.js');

class Router {
    constructor() {
        this.router = require("express").Router(); 
        this.init();
    }

    init() {
        this.router.post("/mailgun", check, success);
    }

    getRouter() {
        return this.router; 
    }
}

module.exports = new Router();