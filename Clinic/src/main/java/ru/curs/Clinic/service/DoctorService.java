package ru.curs.Clinic.service;

import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.curs.Clinic.entity.Doctor;
import ru.curs.Clinic.entity.Talon;

import javax.print.Doc;
import java.util.List;

@Service

public class DoctorService {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    private final EmailService emailService;

    public DoctorService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void addDoctor(Doctor doctor) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(doctor);
        emailService.sendCreation(doctor);
        transaction.commit();
    }

    public void removeDoctor(Long id) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Doctor where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
    }

    public List<Doctor> getDoctors() {
        return session.createQuery("select u from Doctor u", Doctor.class).getResultList();
    }
}
