package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner CONSOLE = new Scanner(System.in);
    private static final Path DATA_FILE_PATH = Path.of("src/resources/data.txt");
    private static final Path ENCRYPTED_FILE_PATH = Path.of("src/resources/encrypted.txt");
    private static final Path DECRYPTED_FILE_PATH = Path.of("src/resources/decrypted.txt");

    private static final String[] DICTIONARY = {" что ", " где ", " когда ", "-то ", "-либо ", "-нибудь ", " из ", "это",
            " по ", " на ", " with ", " when ", " where ", " to ", " on ", " so ", " who ", " that ", " he ",
            " she ", " is ", " them ", " are ", " you "};

    static {
        System.out.println("\nДобро пожаловать в криптоанализатор!");
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            switch (CONSOLE.next()) {
                case "1":
                    resolveEncryptOption();
                    break;
                case "2":
                    resolveDecryptOption();
                    break;
                case "3":
                    resolveBruteforceOption();
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    printActionError();
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                
                Выберите действие:
                
                1) Зашифровать содержимое файла data.txt.
                2) Расшифровать содержимое файла encrypted.txt.
                3) Попытаться взломать.
                4) Выйти из приложения.""");
    }

    private static void resolveEncryptOption() {
        printEncryptOption();
        if (CONSOLE.hasNextInt()) {
            int key = CONSOLE.nextInt();
            try {
                printEncryptSuccess(Files.write(ENCRYPTED_FILE_PATH,
                        Files.readAllLines(DATA_FILE_PATH).stream().map(text ->
                                CaesarCipher.encrypt(text, key)).toList()).toString());
            } catch (IOException e) {
                printEncryptFail(e.getMessage());
            }
        } else {
            printInputKeyEncryptError();
            CONSOLE.next();
        }
    }

    private static void resolveDecryptOption() {
        printDecryptOption();
        if (CONSOLE.hasNextInt()) {
            int key = CONSOLE.nextInt();
            try {
                printDecryptSuccess(Files.write(DECRYPTED_FILE_PATH,
                        Files.readAllLines(ENCRYPTED_FILE_PATH).stream().map(cipherText ->
                                CaesarCipher.decrypt(cipherText, key)).toList()).toString());
            } catch (IOException e) {
                printDecryptFail(e.getMessage());
            }
        } else {
            printInputKeyDecryptError();
            CONSOLE.next();
        }
    }

    private static void resolveBruteforceOption() {
        for (int i = 0; i < CaesarCipher.getAlphabetSize(); i++) {
            int key = i;
            try {
                List<String> decryptedStringList = Files.readAllLines(ENCRYPTED_FILE_PATH).stream().map(cipherText ->
                        CaesarCipher.decrypt(cipherText, key)).toList();
                if (!decryptedStringList.isEmpty() && decryptedStringList.stream().anyMatch(line ->
                        Arrays.stream(DICTIONARY).anyMatch(line.toLowerCase()::contains))) {
                    printBrutForceSuccess(key);
                    return;
                }
            } catch (IOException e) {
                printBrutForceFail(e.getMessage());
            }
        }
        printBrutForceFail("Не найден ключ.");
    }

    private static void printEncryptOption() {
        System.out.println("\nВы хотите зашифровать текст. Введите число, которое будет использоваться для шифрования:");
    }

    private static void printDecryptOption() {
        System.out.println("\nВы хотите расшифровать текст. Введите число, которое будет использоваться для дешифрования:");
    }

    private static void printActionError() {
        System.out.println("\nВведен неверный номер действия.");
    }

    private static void printInputKeyEncryptError() {
        System.out.printf("\nДля шифрования необходимо ввести число (в диапазоне [%d, %d]).%n",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static void printInputKeyDecryptError() {
        System.out.printf("\nДля дешифрования необходимо ввести число (в диапазоне [%d, %d]).%n",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static void printEncryptSuccess(String pathName) {
        System.out.println("\nШифрование выполнено успешно. Результаты смотрите в файле по относительному пути проекта " + pathName + ".");
    }

    private static void printEncryptFail(String failReason) {
        System.out.println("\nНе удалось выполнить шифрование. " + failReason);
    }

    private static void printDecryptSuccess(String pathName) {
        System.out.println("\nДешифрование выполнено успешно. Результаты смотрите в файле по относительному пути проекта " + pathName + ".");
    }

    private static void printDecryptFail(String failReason) {
        System.out.println("\nНе удалось выполнить дешифрование. " + failReason);
    }

    private static void printBrutForceSuccess(int key) {
        System.out.println("\nВзлом произведен успешно. Найден ключ " + key);
    }

    private static void printBrutForceFail(String failReason) {
        System.out.println("\nНе удалось произвести взлом. " + failReason);
    }
}