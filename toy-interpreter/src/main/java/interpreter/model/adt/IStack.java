package interpreter.model.adt;
import interpreter.exceptions.MyException;

public interface IStack<T> {
    T pop() throws MyException;
    void push(T v);
    boolean isEmpty();
}