package code.actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public abstract class CryptoFileAction {

    protected final Scanner scanner;
    protected final Path sourceFilePath;
    protected final Path targetFilePath;

    protected CryptoFileAction(Scanner scanner, Path sourceFilePath, Path targetFilePath) {
        this.scanner = scanner;
        this.sourceFilePath = sourceFilePath;
        this.targetFilePath = targetFilePath;
    }

    protected abstract String executeCryptoOperation(String text, String key);

    protected String executeCryptoAction() throws IOException {
        String key = scanner.nextLine();

        return Files.write(targetFilePath,
                Files.readAllLines(sourceFilePath).stream().map(text ->
                        executeCryptoOperation(text, key)).toList()).toString();
    }
}
