import java.io.IOException;
import java.util.Scanner;
import static java.lang.Integer.*;

public class StringCalculator {
    private static String input;
    private static char operation;
    private static String[] str;

    public static void main(String[] args) throws IOException, NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();

        System.out.println("Введи выражение в поле ниже:");
        System.out.println("Допустимый формат выражений: \"a\" + \"b\", \"a\" - \"b\", \"a\" * b, \"a\" / b.");

        input = scanner.nextLine();
        input = input.replace(" ", "");

        System.out.println("Input: " + input);
        System.out.print("Output: ");

        if (input.contains("+")) {
            str = input.split("\\+");
            operation = '+';
        } else if (input.contains("-")) {
            str = input.split("-");
            operation = '-';
        } else if (input.contains("*")) {
            str = input.split("\\*");
            operation = '*';
        } else if (input.contains("/")) {
            str = input.split("/");
            operation = '/';
        } else throw new IOException("Неверный знак операции!");

        if (str[0].matches("[0-9]")) {
            throw new NumberFormatException("Неверный формат выражения!");
        }

        if (operation == '*' || operation == '/') {
            if (str[1].contains("\"")) throw new IOException("Неверный формат выражения!");
        }

        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].replace("\"", "");
        }

        if ((str[0].length() > 10) || (str[1].length() > 10)) {
            throw new IOException("Длина введенной строки не может превышать 10 символов!");
        }

        switch(operation) {
            case '+': {
                printer.formatPrint(str[0] + str[1]);
                break;
            }
            case '*': {
                int num = parseInt(str[1]);

                if (num < 1 || num > 10) {
                    throw new IOException("Допустимо использовать числа от 1 до 10!");
                }

                int multi = parseInt(str[1]);
                String result = "";

                for (int i = 0; i < multi; i++) {
                    result = result + str[0];
                }
                printer.formatPrint(result);
                break;
            }
            case '-': {
                int idx = str[0].indexOf(str[1]);

                if (idx == -1) {
                    printer.formatPrint(str[0] + "-" + str[1]);
                } else {
                    String minus = str[0].substring(0, idx);
                    minus = minus + str[0].substring(idx + str[1].length());

                    printer.formatPrint(minus);
                }
                break;
            }
            default: {
                int num = parseInt(str[1]);

                if (num < 1 || num > 10) {
                    throw new IOException("Допустимо использовать числа от 1 до 10!");
                }

                int div = str[0].length() / parseInt(str[1]);
                String divStr = str[0].substring(0, div);

                printer.formatPrint(divStr);
                break;
            }
        }
    }
}

class Printer {
    void formatPrint(String str) {
        if (str.length() > 40) {
            System.out.println("\"" + str.substring(0, 40) + "...\"");
        } else {
            System.out.println("\"" + str + "\"");
        }
    }
}
