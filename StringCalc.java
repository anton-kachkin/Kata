package Calc;

import java.util.*;

public class StringCalc {
    static String calc(String input) throws Exception {
        String[] parts = input.split(" ");

        String operand1 = parts[0].replaceAll("\"", "");
        String operator = parts[1];
        String operand2 = parts[2].replaceAll("\"", "");
        String result = "";
        if (parts.length != 3 || input.charAt(0) != '\"' || !operator.matches("[+\\-*/]") || operand1.length() > 10 || operand2.length() > 10) {
            throw new Exception("Неправильный формат выражения");
        }
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                if (!operand1.contains(operand2)) {
                    result = operand1;
                }
                result = operand1.replace(operand2, "");
                break;
            case "*":
                int count = Integer.parseInt(operand2);
                if (count >= 1 && count <= 10) {
                    for (int i = 0; i < count; i++) {
                        result += operand1;
                    }
                }
                break;
            case "/":
                int count2 = Integer.parseInt(operand2);
                int newLength = operand1.length() / count2;
                result = operand1.substring(0, newLength);
                break;
            default:
                throw new Exception("Неправильный формат выражения");
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String input = new Scanner(System.in).nextLine();
        String result = calc(input);
        if (result.length() > 40) {
            System.out.println("\"" + result.substring(0, 40) + "...\"");
        } else {
            System.out.println("\"" + result+ "\"");

        }
    }
}