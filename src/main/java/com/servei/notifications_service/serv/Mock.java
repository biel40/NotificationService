package com.servei.notifications_service.serv;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.nodes.Absence;
import com.servei.notifications_service.nodes.Notification;
import com.servei.notifications_service.nodes.Student;
import com.servei.notifications_service.nodes.Teacher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Mock {
    public static void main(String[] args) {
        Mailgun mg = new Mailgun("Weekly Notifications <weeklynotifications@gmail.com>", "Weekly notifications for: ", "Mailgun");
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Absence absence1 = new Absence();
        absence1.setDate(date.format(dtfDate));
        absence1.setTime(time.format(dtfTime));
        absence1.setSubject("Desplegament");

        Absence absence2 = new Absence();
        absence2.setDate(date.format(dtfDate));
        absence2.setTime(time.format(dtfTime));
        absence2.setSubject("DIW");

        Student student1 = new Student();
        student1.setDNI("4845848468S");
        student1.setName("Berjamin");
        student1.setSurname("Cardona");

        Student student2 = new Student();
        student2.setDNI("4564678468S");
        student2.setName("Joan Guillem");
        student2.setSurname("Cabot");

        student1.hasAbsence(absence1);
        student2.hasAbsence(absence2);

        Notification notification1 = new Notification();
        notification1.setDate(date.format(dtfDate));
        notification1.setTime(time.format(dtfTime));
        notification1.setItWasSent(true);

        notification1.belongsToStudent(student1);

        Notification notification2 = new Notification();
        notification2.setDate(date.format(dtfDate));
        notification2.setTime(time.format(dtfTime));
        notification2.setItWasSent(false);

        notification2.belongsToStudent(student2);

        Teacher teacher = new Teacher();
        teacher.setDNI("45646969P");
        teacher.setMail("ivancaballero9717@gmail.com");
        teacher.setName("Joan");
        teacher.setSurname("Galmes");
        teacher.setPhoneNum("654887548");

        teacher.receiveNotifications(notification1);
        teacher.receiveNotifications(notification2);
        try {
            mg.sendNotification(teacher);
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
    }
}
