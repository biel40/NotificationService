const settings = require("./../model/settings.js");
const mailgun = require("mailgun-js")(settings);
const sender = 'Weekly Notifications <weeklynotifications@gmail.com>';
const format = require('./formatter.js');
module.exports = function ({mail, name, surname, notifications}) {
    return new Promise(function(res, rej) {
        const data = {
            from: sender,
            to: mail,
            subject: `Weekly notification for ${name} ${surname}`,
            html: format(notifications).toHtml()
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