package com.servei.notifications_service.MailProvider;

import com.servei.notifications_service.nodes.Teacher;
import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import net.sargue.mailgun.content.Body;
import org.springframework.beans.factory.annotation.Autowired;

public class MailGun implements MailProvider {

    private Configuration configuration;

    @Autowired
    public MailGun(){

    }

    public void configure() {
        configuration = new Configuration()
                .domain("")
                .apiKey("")
                .from("Test account", "postmaster@somedomain.com");
    }

    @Override
    public boolean sendMail(Teacher teacher) {

        Formatter formatter = new Formatter();
        formatter.setTeacher(teacher);

        return Mail.using(configuration)
                .to(teacher.getMail())
                .subject("This message has an text attachment")
                .html(formatter.toHtml())
                .build()
                .send()
                .isOk();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
