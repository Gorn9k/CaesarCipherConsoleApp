import java.util.Scanner;

public class Main {

    static {
        System.out.println("\nДобро пожаловать в криптоанализатор!");
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        while (true) {
            printMenu();
            switch (console.next()) {
                case "1":
                    printFirstOption();
                    if (console.hasNextInt()) {
                        CaesarCipher.encrypt(console.nextInt());
                        printEncryptSuccess();
                    }
                    else {
                        printInputKeyEncryptError();
                        console.next();
                    }
                    break;
                case "2":
                    printSecondOption();
                    if (console.hasNextInt()) {
                        CaesarCipher.decrypt(console.nextInt());
                        printDecryptSuccess();
                    }
                    else {
                        printInputKeyDecryptError();
                        console.next();
                    };
                    break;
                case "3":
                    printBrutForceSuccess();
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
                
                1) Зашифровать содержимое файла decrypted.txt.
                2) Расшифровать содержимое файла encrypted.txt.
                3) Попытаться взломать.
                4) Выйти из приложения.""");
    }

    private static void printFirstOption() {
        System.out.println("\nВы хотите зашифровать текст. Введите число, которое будет использоваться для шифрования:");
    }

    private static void printSecondOption() {
        System.out.println("\nВы хотите расшифровать текст. Введите число, которое будет использоваться для дешифрования:");
    }

    private static void printActionError() {
        System.out.println("\nВведен неверный номер действия.");
    }

    private static void printInputKeyEncryptError() {
        System.out.println("\nДля шифрования необходимо ввести число.");
    }

    private static void printInputKeyDecryptError() {
        System.out.println("\nДля дешифрования необходимо ввести число.");
    }

    private static void printEncryptSuccess() {
        System.out.println("\nШифрование выполнено успешно. Результаты смотрите в файле encrypted.txt.");
    }

    private static void printDecryptSuccess() {
        System.out.println("\nДешифрование выполнено успешно. Результаты смотрите в файле decrypted.txt.");
    }

    private static void printBrutForceSuccess() {
        System.out.println("\nДля дешифрования необходимо ввести число.");
    }
}