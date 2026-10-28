package interpreter.model.stmt;
import interpreter.model.PrgState;
import interpreter.model.type.IType;
import interpreter.exceptions.MyException;

public record VarDeclStmt(String name, IType type) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        if (symTable.isDefined(name)) throw new MyException("Variable " + name + " already declared");
        symTable.update(name, type.defaultValue());
        return state;
    }
    @Override
    public String toString() { return type + " " + name; }
}