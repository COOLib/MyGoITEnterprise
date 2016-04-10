package ua.goit;

public interface Validator<T> {

    boolean isValid(T result);
}
