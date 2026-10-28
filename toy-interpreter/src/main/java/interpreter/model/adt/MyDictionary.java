package interpreter.model.adt;
import interpreter.exceptions.MyException;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    private Map<K, V> map = new HashMap<>();

    @Override
    public boolean isDefined(K key) { return map.containsKey(key); }

    @Override
    public V lookup(K key) throws MyException {
        if (!isDefined(key)) throw new MyException("Key " + key + " not found");
        return map.get(key);
    }

    @Override
    public void update(K key, V value) { map.put(key, value); }

    @Override
    public String toString() { return map.toString(); }
}