package ru.curs.Clinic.service;

import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.curs.Clinic.entity.Talon;

import java.util.List;

@Service
public class TalonService {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    public void addTalon(Talon talon) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(talon);
        transaction.commit();
    }

    public void removeTalon(Long id) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Talon where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
    }

    public List<Talon> getTalons() {
        return session.createQuery("select u from Talon u", Talon.class).getResultList();
    }
}
