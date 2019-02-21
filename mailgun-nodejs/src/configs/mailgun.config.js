const sendMail = require('./../service/mail-utils.js');
const createMail = require("./../container.js");

module.exports = {
    check: function (req, res, next) {
        if (req.body) {
            next();
        } else {
            res.status(500).send({
                id: "",
                date: new Date(),
                provider: "",
                sent: false,
                msg: "Fields are not correct"
            });
        }

    },
    success: async function (req, res) {
        const {teacher} = createMail(req.body);
        const {notifications} = teacher;

        console.time("sending")
        const result = await sendMail(teacher).catch(err => err);
        console.timeEnd("sending")
        result.idNotifications = notifications ? notifications.map(({id}) => parseInt(id)) : null;

        res.send(result);
    }
}