package interpreter.repository;
import interpreter.model.PrgState;
import interpreter.exceptions.MyException;

public interface IRepository {
    PrgState getCrtPrg() throws MyException;
    void addPrg(PrgState prg);
}