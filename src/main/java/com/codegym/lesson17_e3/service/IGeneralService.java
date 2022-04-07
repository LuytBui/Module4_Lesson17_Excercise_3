package com.codegym.lesson17_e3.service;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void delete(Long id);

}
