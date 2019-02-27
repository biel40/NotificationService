package com.servei.notifications_service.serv.parsers;

import com.servei.notifications_service.nodes.Absence;
import com.servei.notifications_service.nodes.Notification;
import com.servei.notifications_service.nodes.Student;
import com.servei.notifications_service.nodes.Teacher;

public class HTMLParser {
    private Teacher teacher;
    private String html;
    public HTMLParser() {
        this.html = "";
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getHtml() {
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
