package com.softserve.academy.repository;

import com.softserve.academy.utils.MarathonSessionFactory;
import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;
import com.softserve.academy.entity.Sprint;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Repository
public class SprintRepository implements CrudRepository<Sprint, BigInteger> {

    @Override
    public <S extends Sprint> S save(S s) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
        return s;
    }

    @Override
    public <S extends Sprint> Iterable<S> saveAll(Iterable<S> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<S> sprintList = new ArrayList<>();
        for (S s : iterable) {
            session.save(s);
            if (session.contains(s)) {
                sprintList.add(s);
            }
        }
        session.getTransaction().commit();
        session.close();
        return sprintList;
    }

    @Override
    public Optional<Sprint> findById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Sprint sprint = null;
        for (Sprint element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                sprint = element;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        if (sprint != null) {
            return Optional.of(sprint);
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        boolean isExist = StreamSupport.stream(findAll().spliterator(), false)
                .anyMatch(element -> Objects.equals(element.getId(), bigInteger));
        session.getTransaction().commit();
        session.close();
        return isExist;
    }

    @Override
    public Iterable<Sprint> findAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Sprint> result = session.createQuery("from Sprint", Sprint.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Iterable<Sprint> findAllById(Iterable<BigInteger> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Sprint> result = new ArrayList<>();
        for (BigInteger id : iterable) {
            for (Sprint element : findAll()) {
                if (Objects.equals(element.getId(), id)) {
                    result.add(element);
                }
            }
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public long count() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        long number = ((Collection<Sprint>) findAll()).size();
        session.getTransaction().commit();
        session.close();
        return number;
    }

    @Override
    public void deleteById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Sprint element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                session.delete(element);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Sprint sprint) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(sprint);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll(Iterable<? extends Sprint> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Sprint element : iterable) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Sprint element : findAll()) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }
}
