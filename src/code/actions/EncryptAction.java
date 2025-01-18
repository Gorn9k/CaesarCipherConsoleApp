package code.actions;

import code.CaesarCipher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class EncryptAction extends CryptoFileAction implements ActionStrategy {

    public EncryptAction(Scanner scanner, String dataFilePath, String encryptedFilePath) {
        super(scanner, Path.of(dataFilePath), Path.of(encryptedFilePath));
    }

    @Override
    protected String executeCryptoOperation(String text, String key) {
        return CaesarCipher.encrypt(text, Integer.parseInt(key));
    }

    @Override
    public void executeAction() {
        printEncryptOption();
        if (scanner.hasNextInt()) {
            try {
                printEncryptSuccess(executeCryptoAction());
            } catch (IOException e) {
                printEncryptFail(e.getMessage());
            }
        } else {
            printInputKeyEncryptError();
            scanner.nextLine();
        }
    }

    @Override
    public String getActionName() {
        return "Зашифровать содержимое файла %s.".formatted(sourceFilePath.getFileName());
    }

    @Override
    public Integer getActionNumber() {
        return 1;
    }

    private void printEncryptOption() {
        System.out.println("\nВы хотите зашифровать текст. Введите число, которое будет использоваться для шифрования:");
    }
    private void printEncryptSuccess(String pathName) {
        System.out.println("\nШифрование выполнено успешно. Результаты смотрите в файле по относительному пути проекта " + pathName + ".");
    }
    private void printEncryptFail(String failReason) {
        System.out.println("\nНе удалось выполнить шифрование. " + failReason);
    }

    private void printInputKeyEncryptError() {
        System.out.printf("\nДля шифрования необходимо ввести число (в диапазоне [%d, %d]).%n",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
