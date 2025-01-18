package code.actions;

import java.util.List;
import java.util.Scanner;

public class ActionStrategyExecutor {

    private final List<ActionStrategy> actionStrategies;

    public ActionStrategyExecutor(Scanner scanner,
                                  String dataFilePath,
                                  String encryptedFilePath,
                                  String decryptedFilePath,
                                  String[] dictionary) {
        this.actionStrategies = List.of(new EncryptAction(scanner, dataFilePath, encryptedFilePath),
                new DecryptAction(scanner, decryptedFilePath, encryptedFilePath),
                new BruteforceAction(encryptedFilePath, dictionary),
                new ExitAction());
    }

    public void executeActionStrategy(Integer actionNumber) {
        boolean anyActionFound = actionStrategies.stream().anyMatch(actionStrategy -> {
            if (actionStrategy.getActionNumber().equals(actionNumber)) {
                actionStrategy.executeAction();
                return true;
            }
            return false;
        });
        if (!anyActionFound)
            printActionError();
    }

    public void printMenu() {
        StringBuilder str = new StringBuilder();
        actionStrategies.forEach(actionStrategy -> str.append("%d) %s%n"
                .formatted(actionStrategy.getActionNumber(), actionStrategy.getActionName())));
        System.out.printf("""
                
                Выберите действие:
                
                %s
                """, str);
    }

    public void printActionError() {
        System.out.printf("\nВведен неверный номер действия. Допустимый диапазон (%d - %d).%n", 1, actionStrategies.size());
    }
}
