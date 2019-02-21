package com.servei.notifications_service;


import com.servei.notifications_service.models.Constants;
import com.servei.notifications_service.models.SentMail;
import com.servei.notifications_service.nodes.*;
import com.servei.notifications_service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.rmi.CORBA.Stub;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    public void sendmail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("receiver", "ivancaballero9717@gmail.com");
        map.add("subject", "Hola que ase");
        map.add("notification", "test");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        SentMail response = restTemplate.postForObject(Constants.URL_MAILGUN, request, SentMail.class);

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

        Provider provider = new Provider();
        provider.setName("MailGun");

        Notification notification2 = new Notification();
        notification2.setDate(date.format(dtfDate));
        notification2.setTime(time.format(dtfTime));
        notification2.setItWasSent(true);

        notification2.belongsToStudent(student);
        notification2.sendByProvider(provider);

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
