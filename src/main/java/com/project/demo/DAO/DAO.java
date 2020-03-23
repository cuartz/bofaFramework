package com.project.demo.DAO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Carlos Bayona
 */
public interface DAO<T, ID extends Serializable> {

    T findById(ID id, boolean lock);

    List<T> findAll();

    List<T> findByExample(T exampleInstance, String[] excludeProperty);

    T makePersistent(T entity);

    void makeTransient(T entity);
}
