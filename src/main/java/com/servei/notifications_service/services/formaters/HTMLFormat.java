package com.servei.notifications_service.services.formaters;

import com.servei.notifications_service.nodes.Absence;
import com.servei.notifications_service.nodes.Notification;
import com.servei.notifications_service.nodes.Student;
import com.servei.notifications_service.nodes.Teacher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class HTMLFormat implements Formatter {

    private String html = "";

    @Override
    public String format(Teacher teacher) {
         html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Notification</title>\n" +
                "    <style>\n" +
                "\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            background-color: #ddd;\n" +
                "            font-family: 'Source Sans Pro';\n" +
                "            font-weight: 300;\n" +
                "        }\n" +
                "\n" +
                "        .Message {\n" +
                "            display: table;\n" +
                "            position: relative;\n" +
                "            margin: 20px auto 0;\n" +
                "            width: 500px;\n" +
                "            background-color: whitesmoke;\n" +
                "            color: black;\n" +
                "            transition: all 0.2s ease;\n" +
                "            border-radius: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .Message.is-hidden {\n" +
                "            opacity: 0;\n" +
                "            height: 0;\n" +
                "            font-size: 0;\n" +
                "            padding: 0;\n" +
                "            margin: 0 auto;\n" +
                "            display: block;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        .Message--red {\n" +
                "            background-color: #b33939;\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "            color: white;\n" +
                "        }\n" +
                "\n" +
                "        .Message-icon {\n" +
                "            display: table-cell;\n" +
                "            vertical-align: middle;\n" +
                "            width: 20px;\n" +
                "            padding: 10px;\n" +
                "            text-align: center;\n" +
                "            background-color: rgba(0, 0, 0, 0.25);\n" +
                "            border-radius: 10px 0px 0px 10px;\n" +
                "        }\n" +
                "\n" +
                "        .Message-body {\n" +
                "            display: table-cell;\n" +
                "            vertical-align: middle;\n" +
                "            padding: 5px 25px 5px 25px;\n" +
                "        }\n" +
                "\n" +
                "        li {\n" +
                "            list-style: none;\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"Message\" id=\"js-timer\">\n" +
                "        <div class=\"Message-icon\">\n" +
                "        </div>\n" +
                "        <div class=\"Message-body\">\n" +
                "            <h1 style=\"color: black;\">Hola "+teacher.getName()+",</h1>\n" +
                "            <p style=\"color: black; font-size: 20px;\">Aquí tens els retards que falten validar</p>\n" +
                "            \n" + this.getHtml(teacher) +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return html;
    }

    private String getHtml(Teacher teacher) {
        for (Notification notification : teacher.getNotifications()) {
            this.html += "<div style=\"padding-bottom: 15px;\" class=\"Message Message--red\">\n" +
                    "<img src=\"http://172.16.7.228:3000/"+notification.getId()+"/empty.png\" alt=\"\">\n" +
                    "\n" +
                    "            <div class=\"Message-icon\">\n" +
                    "            </div>\n" +
                    "            <div class=\"Message-body\">\n" +
                    this.getStudents(notification) +
                    "            </div>\n" +
                    "        </div>";
        }
        return html;
    }

    private String getStudents(Notification notification) {
        StringBuilder html = new StringBuilder();
        for (Student student : notification.getStudents()) {
            html.append("<h4 style=\"color: white; font-size: 14pt !important; margin: 0px; padding: 10px 10px 5px 10px;\">Faltes d'en ")
                    .append(student.getName())
                    .append(" ")
                    .append(student.getSurname())
                    .append("</h4>\n")
                    .append("<ul style=\"margin: 0px; padding: 5px 10px 10px 10px;\">\n")
                    .append(this.getAbsences(student))
                    .append("</ul>");
        }
        return html.toString();
    }

    private String getAbsences(Student student) {
        StringBuilder html = new StringBuilder();
        for (Absence absence : student.getAbsences()) {
            html.append("<li style=\"font-size: 12pt;\">")
                    .append(absence.getSubject())
                    .append(" - ")
                    .append(absence.getDate())
                    .append("-")
                    .append(absence.getTime())
                    .append("</li>");
        }
        return html.toString();
    }
}
