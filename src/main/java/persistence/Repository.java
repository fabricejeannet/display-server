package persistence;

import java.util.List;

/**
 * Created by fabricejeannet on 05/04/15.
 */
public interface Repository <T extends Root> {
    T get(Object id) throws UnkownElementException;
    void delete(T entity);
    void add(T entity);
    List<T> all();
}
