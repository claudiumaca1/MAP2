package interpreter.view;
import interpreter.controller.Controller;
import interpreter.exceptions.MyException;

public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String key, String description, Controller ctrl) {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute() {
        try {
            ctrl.allStep();
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}