const settings = require("./../model/settings.js");
const mailgun = require("mailgun-js")(settings);
const sender = 'Weekly Notifications <weeklynotifications@gmail.com>';
const format = require('./formatter.js');

module.exports = function (teacher) {
    return new Promise(function(res, rej) {
        const data = {
            from: sender,
            to: teacher.mail,
            subject: `Weekly notification for ${teacher.name} ${teacher.surname}`,
            html: format(teacher).toHtml()
        };
        mailgun.messages().send(data, function (error, body) {
            if (error) {
                rej({
                    provider: 'Mailgun',
                    id: null,
                    sent: false,
                    date: new Date()
                });
            }
            res({
                provider: 'Mailgun',
                id: body.id,
                sent: true,
                date: new Date()
            });
        });
    })
}