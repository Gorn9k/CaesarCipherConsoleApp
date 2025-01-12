package code;

import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {

    private static final List<Character> SYMBOLS = new ArrayList<>();

    static {
        addRange('0', '9');
        addRange('A', 'Z');
        addRange('a', 'z');
        addRange('А', 'Я');
        addRange('а', 'я');
        SYMBOLS.add('Ё');
        SYMBOLS.add('ё');
        for (char c = 33; c <= 126; c++) {
            if (!Character.isLetterOrDigit(c)) {
                SYMBOLS.add(c);
            }
        }
    }

    public static String encrypt(String text, int key) {
        return shiftSymbols(text, key);
    }

    public static String decrypt(String cipherText, int key) {
        return shiftSymbols(cipherText, -key);
    }

    private static String shiftSymbols(String text, int key) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder();

        if (Math.abs(key) > SYMBOLS.size()) {
            key %= SYMBOLS.size();
        }

        for (int i = 0; i < text.length(); i++) {
            result.append(shiftSymbol(text.charAt(i), key));
        }

        return result.toString();
    }

    private static Character shiftSymbol(char currentChar, int key) {
        int symbolIndex = SYMBOLS.indexOf(currentChar);

        if (symbolIndex == -1)
            return currentChar;
        else {
            int shiftedSymbolIndex = (symbolIndex + key) % SYMBOLS.size();

            if (shiftedSymbolIndex < 0) {
                shiftedSymbolIndex += SYMBOLS.size();
            }

            return SYMBOLS.get(shiftedSymbolIndex);
        }
    }

    private static void addRange(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (Character.isDefined(i)) {
                CaesarCipher.SYMBOLS.add((char) i);
            }
        }
    }

    public static int getAlphabetSize() {
        return CaesarCipher.SYMBOLS.size();
    }
}
