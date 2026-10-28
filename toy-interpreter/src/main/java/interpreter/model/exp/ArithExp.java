package interpreter.model.exp;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.exceptions.MyException;

public record ArithExp(char op, IExp left, IExp right) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        IValue v1 = left.eval(symTable);
        if (!(v1 instanceof IntValue(int n1))) throw new MyException("First operand is not an int");

        IValue v2 = right.eval(symTable);
        if (!(v2 instanceof IntValue(int n2))) throw new MyException("Second operand is not an int");

        return switch (op) {
            case '+' -> new IntValue(n1 + n2);
            case '-' -> new IntValue(n1 - n2);
            case '*' -> new IntValue(n1 * n2);
            case '/' -> {
                if (n2 == 0) throw new MyException("Division by zero");
                yield new IntValue(n1 / n2);
            }
            default -> throw new MyException("Invalid operator");
        };
    }
    @Override
    public String toString() { return "(" + left + " " + op + " " + right + ")"; }
}