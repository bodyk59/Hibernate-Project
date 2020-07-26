package com.softserve.academy.repository;

import com.softserve.academy.model.Task;
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
public class TaskRepository implements CrudRepository<Task, BigInteger> {

    @Override
    public <S extends Task> S save(S s) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
        return s;
    }

    @Override
    public <S extends Task> Iterable<S> saveAll(Iterable<S> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<S> taskList = new ArrayList<>();
        for (S s : iterable) {
            session.save(s);
            if (session.contains(s)) {
                taskList.add(s);
            }
        }
        session.getTransaction().commit();
        session.close();
        return taskList;
    }

    @Override
    public Optional<Task> findById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Task task = null;
        for (Task element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                task = element;
                break;
            }
        }
        session.getTransaction().commit();
        session.close();
        if (task != null) {
            return Optional.of(task);
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
    public Iterable<Task> findAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery("from Task ", Task.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Iterable<Task> findAllById(Iterable<BigInteger> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Task> result = new ArrayList<>();
        for (BigInteger id : iterable) {
            for (Task element : findAll()) {
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
        long number = ((Collection<Task>) findAll()).size();
        session.getTransaction().commit();
        session.close();
        return number;
    }

    @Override
    public void deleteById(BigInteger bigInteger) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Task element : findAll()) {
            if (Objects.equals(element.getId(), bigInteger)) {
                session.delete(element);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Task task) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll(Iterable<? extends Task> iterable) {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Task element : iterable) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = MarathonSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Task element : findAll()) {
            session.delete(element);
        }
        session.getTransaction().commit();
        session.close();
    }
}
