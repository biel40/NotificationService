package com.servei.notifications_service.services.parsers;

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
        String html = "";
        for (Student student : notification.getStudents()) {
            html += "<h4 style=\"color: white; font-size: 14pt !important; margin: 0px; padding: 10px 10px 5px 10px;\">Faltes d'en "
                    +student.getName() + " " +student.getSurname() + "</h4>\n" +
                    "            <ul style=\"margin: 0px; padding: 5px 10px 10px 10px;\">\n" +
                    this.getAbsences(student) +
                    "            </ul>";
        }
        return html;
    }

    private String getAbsences(Student student) {
        String html = "";
        for (Absence absence : student.getAbsences()) {
            html += "<li style=\"font-size: 12pt;\">"+absence.getSubject() +" - "+
                    absence.getDate() + "-"+absence.getTime()+"</li>";
        }
        return html;
    }
}
