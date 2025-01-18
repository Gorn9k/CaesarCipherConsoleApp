package code;

import code.actions.ActionStrategyExecutor;

import java.util.Scanner;

public class Main {

    static {
        System.out.println("\nДобро пожаловать в криптоанализатор!");
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        var actionStrategyExecutor = new ActionStrategyExecutor(console,
                "src/resources/data.txt",
                "src/resources/encrypted.txt",
                "src/resources/decrypted.txt",
                new String[]{" что ", " где ", " когда ", "-то ", "-либо ", "-нибудь ", " из ", "это",
                        " по ", " на ", " with ", " when ", " where ", " to ", " on ", " so ", " who ", " that ", " he ",
                        " she ", " is ", " them ", " are ", " you "});
        while (true) {
            actionStrategyExecutor.printMenu();
            if (console.hasNextInt()) {
                actionStrategyExecutor.executeActionStrategy(Integer.parseInt(console.nextLine()));
            }
            else {
                actionStrategyExecutor.printActionError();
                console.nextLine();
            }
        }
    }
}