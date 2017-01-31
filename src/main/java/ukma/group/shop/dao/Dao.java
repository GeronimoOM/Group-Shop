package ukma.group.shop.dao;

import java.util.List;

public interface Dao<E, K> {

    E find(K key);

    List<E> findAll();

    void persist(E entity);

    void update(E entity);

    void delete(K key);

}
