package ua.goit;

public interface Task<T> {

    void execute();

    T getResult();
}
