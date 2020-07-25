package com.softserve.academy.repository;

import com.softserve.academy.entity.CustomEntity;
import com.softserve.academy.utils.MarathonSessionFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Repository
public class MarathonRepository implements CrudRepository<CustomEntity, BigInteger> {

    @Override
    public <S extends CustomEntity> S save(S s) {
        MarathonSessionFactory.getSessionFactory().openSession().saveOrUpdate(s);
        return s;
    }

    @Override
    public <S extends CustomEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CustomEntity> findById(BigInteger bigInteger) {
        return Optional.of(MarathonSessionFactory
                .getSessionFactory()
                .openSession()
                .get(CustomEntity.class, bigInteger));
    }

    @Override
    public boolean existsById(BigInteger bigInteger) {
        return false;
    }

    @Override
    public Iterable<CustomEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<CustomEntity> findAllById(Iterable<BigInteger> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(BigInteger bigInteger) {

    }

    @Override
    public void delete(CustomEntity customEntity) {
        MarathonSessionFactory.getSessionFactory()
                .openSession()
                .delete(customEntity);
    }

    @Override
    public void deleteAll(Iterable<? extends CustomEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
