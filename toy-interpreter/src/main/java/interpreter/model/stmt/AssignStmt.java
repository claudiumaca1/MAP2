package interpreter.model.stmt;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.exceptions.MyException;

public record AssignStmt(String id, IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        if (!symTable.isDefined(id)) throw new MyException("Variable " + id + " not declared");

        IValue val = exp.eval(symTable);
        if (!val.getType().equals(symTable.lookup(id).getType()))
            throw new MyException("Type mismatch for variable " + id);

        symTable.update(id, val);
        return state;
    }
    @Override
    public String toString() { return id + " = " + exp; }
}