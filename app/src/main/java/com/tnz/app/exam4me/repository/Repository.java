package com.tnz.app.exam4me.repository;

import java.util.Set;

/**
 * Created by Admin on 2016/04/23.
 */

public interface Repository<E, ID> {

    E findById(ID id);

    E insert(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}