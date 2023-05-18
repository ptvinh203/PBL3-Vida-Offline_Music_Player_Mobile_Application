package com.pbl3.musicapplication.service;

import java.util.List;

public interface GenericService<T, U> {
    T create(U model);

    T update(Integer id, U model);

    void deleteById(Integer id);

    void deleteAll();

    U findById(Integer id);

    List<U> findAll();

    long count();
}
