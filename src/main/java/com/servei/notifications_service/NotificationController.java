package com.servei.notifications_service;

import com.servei.notifications_service.models.Constants;
import com.servei.notifications_service.models.SentMail;
import com.servei.notifications_service.nodes.*;
import com.servei.notifications_service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

    @RequestMapping("/sendmail")
    public synchronized void sendmail() throws NullPointerException{
        Teacher teacher = getTeacher();
        SentMail response = null;

        try {
             response = restTemplate.postForObject(Constants.URL_MAILGUN, teacher, SentMail.class);
        } catch (Exception ignored){}

        if (response == null || response.getIdNotifications() == null) {
            throw new NullPointerException("Error to send notifications");
        }

        System.out.println(response.toString());

        Iterable<Notification> notifies = notificationRepository.findAllById(response.getIdNotifications());

        for (Notification notify : notifies) {
            Provider provider = new Provider();
            provider.setName(response.getProvider());

            notify.sentByProvider(provider);
            notificationRepository.save(notify);
        }
    }

    @RequestMapping(value = "/setRead", method = RequestMethod.POST)
    public void setRead(@RequestBody Long id){
        Optional<Notification> notifications = notificationRepository.findById(id);
        notifications.ifPresent(notification -> {
                notification.setRead(true);
                notificationRepository.save(notification);
        });
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
        teacher.setMail("jgcabotd@gmail.com");
        teacher.setName("Joan");
        teacher.setSurname("Galmes");
        teacher.setPhoneNum("654887548");

        teacher.receiveNotifications(notification1);
        teacher.receiveNotifications(notification2);

        teacherRepository.save(teacher);

        return teacher;
    }


}
