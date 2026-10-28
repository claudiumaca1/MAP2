package interpreter;

import interpreter.controller.Controller;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.ArithExp;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;
import interpreter.model.stmt.AssignStmt;
import interpreter.model.stmt.CompStmt;
import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.PrintStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import interpreter.view.RunExample;
import interpreter.view.TextMenu;

public class Main {
    public static void main(String[] args) {
        //EXAMPLE 1: int v; v=2; print(v)
        IStmt ex1 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))
                )
        );
        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1);
        Repository repo1 = new Repository();
        repo1.addPrg(prg1);
        Controller ctrl1 = new Controller(repo1, true);

        //EXAMPLE 2: int a; int b; a=2+3*5; b=a+1; print(b)
        IStmt ex2 = new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(
                        new VarDeclStmt("b", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                                        new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(
                                        new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                                        new PrintStmt(new VarExp("b"))
                                )
                        )
                )
        );
        PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2);
        Repository repo2 = new Repository();
        repo2.addPrg(prg2);
        Controller ctrl2 = new Controller(repo2, true);

        //EXMPLE 3: int v; v=2; print(v/0) (Tests Error Handling / Division by Zero)
        IStmt ex3 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new ArithExp('/', new VarExp("v"), new ValueExp(new IntValue(0))))
                )
        );
        PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3);
        Repository repo3 = new Repository();
        repo3.addPrg(prg3);
        Controller ctrl3 = new Controller(repo3, true);

        //TEXT MENU SETUP
        TextMenu menu = new TextMenu();
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        menu.addCommand(new interpreter.view.Command("0", "exit") {
            @Override
            public void execute() { System.exit(0); }
        });

        menu.show();
    }
}