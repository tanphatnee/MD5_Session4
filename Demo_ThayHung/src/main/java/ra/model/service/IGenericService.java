package ra.model.service;

import java.util.List;

public interface IGenericService <T,E>{
    List<T> findAll();
    T findByID(E id);
    T save(T p);
    T delete(E id);
}
