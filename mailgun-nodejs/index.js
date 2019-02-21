// Global server
const express = require("express");
const app = express();
const server = require("http").createServer(app);
const bodyParser = require("body-parser");
const morgan = require('morgan');
// Routers
const router = require('./src/router.js').getRouter();

// Server params
const ip = "172.16.7.228";
const port = 3000;

// Set up basic middlewares
app.set('json spaces', 40);
//app.use(morgan("dev"))
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(bodyParser.json());

// Routes for each service
app.use("/notify", router);

// Error 404 handler
app.use(function (req, res) {
    res.status(404).send("I think you got the wrong path mate")
});

// Start server
server.listen(port, ip, function() {
    console.log(`http://${ip}:${port}`);
})