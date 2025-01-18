package code.actions;

import code.CaesarCipher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class BruteforceAction implements ActionStrategy {

    private final Path encryptedFilePath;
    private final String[] dictionary;

    public BruteforceAction(String encryptedFilePath, String[] dictionary) {
        this.encryptedFilePath = Path.of(encryptedFilePath);
        this.dictionary = dictionary;
    }

    @Override
    public void executeAction() {
        for (int i = 0; i < CaesarCipher.getAlphabetSize(); i++) {
            int key = i;
            try {
                List<String> decryptedStringList = Files.readAllLines(encryptedFilePath).stream().map(cipherText ->
                        CaesarCipher.decrypt(cipherText, key)).toList();
                if (!decryptedStringList.isEmpty() && decryptedStringList.stream().anyMatch(line ->
                        Arrays.stream(dictionary).anyMatch(line.toLowerCase()::contains))) {
                    printBrutForceSuccess(key);
                    return;
                }
            } catch (IOException e) {
                printBrutForceFail(e.getMessage());
            }
        }
        printBrutForceFail("Не найден ключ.");
    }

    @Override
    public String getActionName() {
        return "Попытаться взломать.";
    }

    @Override
    public Integer getActionNumber() {
        return 3;
    }

    private void printBrutForceSuccess(int key) {
        System.out.println("\nВзлом произведен успешно. Найден ключ " + key);
    }

    private void printBrutForceFail(String failReason) {
        System.out.println("\nНе удалось произвести взлом. " + failReason);
    }
}
