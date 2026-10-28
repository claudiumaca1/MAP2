package interpreter;

import interpreter.exceptions.MyException;
import interpreter.model.adt.IStack;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyDictionary;
import interpreter.model.exp.ArithExp;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {

    @Test
    public void testStackOperations() throws MyException {
        IStack<Integer> stack = new MyStack<>();
        assertTrue(stack.isEmpty());
        stack.push(42);
        assertFalse(stack.isEmpty());
        assertEquals(42, stack.pop());
    }

    @Test
    public void testDivisionByZeroException() {
        ArithExp divExp = new ArithExp('/', new ValueExp(new IntValue(10)), new ValueExp(new IntValue(0)));
        MyDictionary<String, interpreter.model.value.IValue> symTable = new MyDictionary<>();

        assertThrows(MyException.class, () -> divExp.eval(symTable));
    }
}