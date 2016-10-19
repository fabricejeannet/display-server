package command;

/**
 * Created by fabricejeannet on 07/04/15.
 */
public interface Command<T> {
    T execute();
}
