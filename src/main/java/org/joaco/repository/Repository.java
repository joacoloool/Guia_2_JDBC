package org.joaco.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {

 public void save(T t) throws SQLException;
 public Optional <List<T>> findAll() throws SQLException;
 public void update(T t)throws SQLException;
 public boolean delete(T t)throws SQLException;
 public Optional<T> findById(int id) throws SQLException;
}
