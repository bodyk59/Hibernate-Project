package com.softserve.academy.repository;

import com.softserve.academy.model.Progress;
import com.softserve.academy.utils.MarathonSessionFactory;
import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Repository
public class ProgressRepository implements CrudRepository<Progress, BigInteger> {

    @Override
    public <S extends Progress> S save(S s) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
        return s;
    }

    @Override
    public <S extends Progress> Iterable<S> saveAll(Iterable<S> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<S> progressList = new ArrayList<>();
        for (S s : iterable) {
            session.save(s);
            if (session.contains(s)) {
                progressList.add(s);
            }
        }
        session.getTransaction().commit();
        session.close();
        return progressList;
    }

    @Override
    public Optional<Progress> findById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Progress progress = null;
        for (Progress element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                progress = element;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        if (progress != null) {
            return Optional.of(progress);
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
    public Iterable<Progress> findAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Progress> result = session.createQuery("from Progress ", Progress.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Iterable<Progress> findAllById(Iterable<BigInteger> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Progress> result = new ArrayList<>();
        for (BigInteger id : iterable) {
            for (Progress element : findAll()) {
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
        long number = ((Collection<Progress>) findAll()).size();
        session.getTransaction().commit();
        session.close();
        return number;
    }

    @Override
    public void deleteById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Progress element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                session.delete(element);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Progress progress) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(progress);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll(Iterable<? extends Progress> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Progress element : iterable) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Progress element : findAll()) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }
}
