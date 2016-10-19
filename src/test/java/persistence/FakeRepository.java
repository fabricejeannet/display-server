package persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabricejeannet on 05/04/15.
 */
public class FakeRepository <T extends Root> implements Repository<T> {

    public T get(Object id) throws UnkownElementException {
        for (T element : elements) {
            if(element.getId().equals(id)) {
                return element;
            }
        }
        throw new UnkownElementException();
    }


    public void delete(T entity) {
        elements.remove(entity);
    }

    public void add(T entity) {
        elements.add(entity);
    }

    public List<T> all() {
        return elements;
    }

    private List<T> elements = new ArrayList<T>();

}
