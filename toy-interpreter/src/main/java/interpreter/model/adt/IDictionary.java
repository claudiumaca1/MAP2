package interpreter.model.adt;
import interpreter.exceptions.MyException;

public interface IDictionary<K, V> {
    boolean isDefined(K key);
    V lookup(K key) throws MyException;
    void update(K key, V value);
}