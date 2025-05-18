package com.example.bootcampprojectcqrs.application.services;

import java.util.List;

public interface BaseService<T, ID> {
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
    T getById(ID id);
    List<T> getAll();
} 