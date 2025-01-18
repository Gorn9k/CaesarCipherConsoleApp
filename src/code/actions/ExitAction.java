package code.actions;

public class ExitAction implements ActionStrategy {
    @Override
    public void executeAction() {
        System.exit(0);
    }

    @Override
    public String getActionName() {
        return "Выйти из приложения.";
    }

    @Override
    public Integer getActionNumber() {
        return 4;
    }
}
