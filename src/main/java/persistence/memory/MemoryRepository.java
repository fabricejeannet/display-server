package persistence.memory;

import persistence.Repository;
import persistence.Root;
import persistence.UnkownElementException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabricejeannet on 13/10/2016.
 */
public class MemoryRepository<T extends Root> implements Repository<T> {
    @Override
    public T get(Object id) throws UnkownElementException {
        for (T element : elements) {
            if(element.getId().equals(id)) {
                return element;
            }
        }
        throw new UnkownElementException();
    }

    @Override
    public void delete(T entity) {
        elements.remove(entity);
    }

    @Override
    public void add(T entity) {
        elements.add(entity);
    }

    @Override
    public List<T> all() {
        return elements;
    }

    private List<T> elements = new ArrayList<T>();
}
