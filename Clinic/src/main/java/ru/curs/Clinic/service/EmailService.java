package ru.curs.Clinic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.curs.Clinic.entity.Doctor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Slf4j
@Aspect
@Service
@RequiredArgsConstructor
public class EmailService {

    public static final String EMAIL = "artem2003one@yandex.ru";
    private final JavaMailSender javaMailSender;
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @After("createServiceMethods() && args(talon)") //выполняем после create methods
    @Async
    public void sendCreation(Doctor talon) {
        sendCreationNote(talon);
    }

    @Pointcut("execution(public void ru.curs.Clinic.service.*Service.create(..))")
    public void createServiceMethods() {}

    @Async
    private void sendCreationNote(Object o) throws MailException { //отправляем письмо
        log.info("Sending email about new {}", o);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(EMAIL);
        mail.setFrom(EMAIL);
        mail.setSubject("Приём на работу " + o.getClass().getSimpleName());
        mail.setText("В " + sdf.format(timestamp.getTime()) + " был(а) принят(а) на работу " + o);
        javaMailSender.send(mail);

        log.info("Email about {} has been sent", o);
    }


}
