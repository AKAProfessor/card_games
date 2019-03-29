package com.intuit.cg.dao;

import java.util.List;

/**
 *
 * @author sakhim
 */
public interface Dao<T> {

    List<T> getAll();

    void saveAll(List<T> tList);

}
