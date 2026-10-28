package interpreter.repository;
import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> prgStates = new ArrayList<>();

    @Override
    public void addPrg(PrgState prg) { prgStates.add(prg); }

    @Override
    public PrgState getCrtPrg() throws MyException {
        if (prgStates.isEmpty()) throw new MyException("Repository is empty");
        return prgStates.get(0);
    }
}