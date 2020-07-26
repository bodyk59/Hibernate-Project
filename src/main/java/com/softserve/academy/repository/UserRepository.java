package com.softserve.academy.repository;

import com.softserve.academy.model.Users;
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
public class UserRepository implements CrudRepository<Users, BigInteger> {

    @Override
    public <S extends Users> S save(S s) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
        return s;
    }

    @Override
    public <S extends Users> Iterable<S> saveAll(Iterable<S> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<S> usersList = new ArrayList<>();
        for (S s : iterable) {
            session.save(s);
            if (session.contains(s)) {
                usersList.add(s);
            }
        }
        session.getTransaction().commit();
        session.close();
        return usersList;
    }

    @Override
    public Optional<Users> findById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Users users = null;
        for (Users element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                users = element;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        if (users != null) {
            return Optional.of(users);
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
    public Iterable<Users> findAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Users> result = session.createQuery("from Users", Users.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Iterable<Users> findAllById(Iterable<BigInteger> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Users> result = new ArrayList<>();
        for (BigInteger id : iterable) {
            for (Users element : findAll()) {
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
        long number = ((Collection<Users>) findAll()).size();
        session.getTransaction().commit();
        session.close();
        return number;
    }

    @Override
    public void deleteById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Users element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                session.delete(element);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Users users) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(users);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll(Iterable<? extends Users> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Users element : iterable) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Users element : findAll()) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }
}
