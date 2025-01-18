package code.actions;

import code.CaesarCipher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class DecryptAction extends CryptoFileAction implements ActionStrategy {

    public DecryptAction(Scanner scanner, String decryptedFilePath, String encryptedFilePath) {
        super(scanner, Path.of(encryptedFilePath), Path.of(decryptedFilePath));
    }

    @Override
    protected String executeCryptoOperation(String text, String key) {
        return CaesarCipher.decrypt(text, Integer.parseInt(key));
    }

    @Override
    public void executeAction() {
        printDecryptOption();
        if (scanner.hasNextInt()) {
            try {
                printDecryptSuccess(executeCryptoAction());
            } catch (IOException e) {
                printDecryptFail(e.getMessage());
            }
        } else {
            printInputKeyDecryptError();
            scanner.nextLine();
        }
    }

    @Override
    public String getActionName() {
        return "Расшифровать содержимое файла %s.".formatted(sourceFilePath.getFileName());
    }

    @Override
    public Integer getActionNumber() {
        return 2;
    }

    private void printDecryptOption() {
        System.out.println("\nВы хотите расшифровать текст. Введите число, которое будет использоваться для дешифрования:");
    }
    private void printDecryptSuccess(String pathName) {
        System.out.println("\nДешифрование выполнено успешно. Результаты смотрите в файле по относительному пути проекта " + pathName + ".");
    }
    private void printDecryptFail(String failReason) {
        System.out.println("\nНе удалось выполнить дешифрование. " + failReason);
    }

    private void printInputKeyDecryptError() {
        System.out.printf("\nДля дешифрования необходимо ввести число (в диапазоне [%d, %d]).%n",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
