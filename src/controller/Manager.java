package controller;

public interface Manager<T> {

    boolean create(T t);

    void read();

    boolean update(T t);

    boolean delete(int number);
}
