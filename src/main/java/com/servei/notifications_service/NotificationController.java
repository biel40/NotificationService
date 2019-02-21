package com.servei.notifications_service;


import com.servei.notifications_service.models.Constants;
import com.servei.notifications_service.models.SentMail;
import com.servei.notifications_service.nodes.*;
import com.servei.notifications_service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class NotificationController {

    private RestTemplate restTemplate;
    private TeacherRepository teacherRepository;
    private NotificationRepository notificationRepository;
    private StudentRepository studentRepository;
    private ProviderRepository providerRepository;
    private AbsenceRepository absenceRepository;

    @Autowired
    public NotificationController(RestTemplate restTemplate,
                                  TeacherRepository teacherRepository,
                                  NotificationRepository notificationRepository,
                                  StudentRepository studentRepository,
                                  AbsenceRepository absenceRepository,
                                  ProviderRepository providerRepository) {
        this.restTemplate = restTemplate;
        this.teacherRepository = teacherRepository;
        this.notificationRepository = notificationRepository;
        this.absenceRepository = absenceRepository;
        this.providerRepository = providerRepository;
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/sendmail")
    public synchronized SentMail sendmail() throws NullPointerException{
        Teacher teacher = getTeacher();
        SentMail response = restTemplate.postForObject(Constants.URL_MAILGUN, teacher, SentMail.class);

        if (response == null || response.getIdNotifications() == null) {
            throw new NullPointerException("Error to send notifications");
        }

        Iterable<Notification> notifies = notificationRepository.findAllById(response.getIdNotifications());

        for (Notification notify : notifies) {
            Provider provider = new Provider();
            provider.setName(response.getProvider());

            notify.sendByProvider(provider);
            notificationRepository.save(notify);
        }

        return response;
    }

    @RequestMapping("/getTeacher")
    public Teacher getTeacher() {

        teacherRepository.deleteAll();
        notificationRepository.deleteAll();
        studentRepository.deleteAll();
        providerRepository.deleteAll();
        absenceRepository.deleteAll();

        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Notification notification1 = new Notification();
        notification1.setDate(date.format(dtfDate));
        notification1.setTime(time.format(dtfTime));
        notification1.setItWasSent(false);

        Absence absence = new Absence();
        absence.setDate(date.format(dtfDate));
        absence.setTime(time.format(dtfTime));
        absence.setSubject("Desplegamanet");

        Student student = new Student();
        student.setDNI("4845848468S");
        student.setName("Berjamin");
        student.setSurname("Cardona");

        student.hasAbsence(absence);

        Notification notification2 = new Notification();
        notification2.setDate(date.format(dtfDate));
        notification2.setTime(time.format(dtfTime));
        notification2.setItWasSent(true);

        notification2.belongsToStudent(student);

        Teacher teacher = new Teacher();
        teacher.setDNI("45646969P");
        teacher.setMail("jgcabotd@gmail.com");
        teacher.setName("Joan Guillem");
        teacher.setSurname("Cabot Dols");
        teacher.setPhoneNum("86598948");

        teacher.receiveNotifications(notification1);

        teacherRepository.save(teacher);

        teacher = teacherRepository.findByName("Joan Guillem");

        teacher.receiveNotifications(notification2);

        //The method save() also update nodes and create nodes.
        teacherRepository.save(teacher);

        return teacher;
    }


}
