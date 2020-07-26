package com.softserve.academy.repository;

import com.softserve.academy.entity.Marathon;
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
public class MarathonRepository implements CrudRepository<Marathon, BigInteger> {

    @Override
    public <S extends Marathon> S save(S s) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
        return s;
    }

    @Override
    public <S extends Marathon> Iterable<S> saveAll(Iterable<S> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<S> marathonList = new ArrayList<>();
        for (S s : iterable) {
            session.save(s);
            if (session.contains(s)) {
                marathonList.add(s);
            }
        }
        session.getTransaction().commit();
        session.close();
        return marathonList;
    }

    @Override
    public Optional<Marathon> findById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Marathon marathon = null;
        for (Marathon element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                marathon = element;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        if (marathon != null) {
            return Optional.of(marathon);
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
    public Iterable<Marathon> findAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Marathon> result = session.createQuery("from Marathon", Marathon.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Iterable<Marathon> findAllById(Iterable<BigInteger> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Marathon> result = new ArrayList<>();
        for (BigInteger id : iterable) {
            for (Marathon element : findAll()) {
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
        long number = ((Collection<Marathon>) findAll()).size();
        session.getTransaction().commit();
        session.close();
        return number;
    }

    @Override
    public void deleteById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Marathon element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                session.delete(element);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Marathon marathon) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(marathon);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll(Iterable<? extends Marathon> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Marathon element : iterable) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Marathon element : findAll()) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }
}
