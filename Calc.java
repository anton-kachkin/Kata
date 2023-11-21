package Calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// git testing
class Main {
    public static String calc(String input) {

        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неправильный формат выражения");
        }

        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];

        int num1, num2;
        boolean isRoman = false;
        try {
            num1 = Integer.parseInt(operand1);
            num2 = Integer.parseInt(operand2);
            if (!operand1.matches("\\d+") || !operand2.matches("\\d+")) {
                throw new IllegalArgumentException("Оба операнда должны быть одинакового формата");
            }
        } catch (NumberFormatException e) {
            if (!operand1.matches("[IVX]+") || !operand2.matches("[IVX]+")) {
                throw new IllegalArgumentException("Оба операнда должны быть одинакового формата");
            }
            num1 = RomanToDecimal(operand1);
            num2 = RomanToDecimal(operand2);
            isRoman = true;
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }

        int result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        };

        if (isRoman) {
            if (result <= 0) {
                throw new IllegalArgumentException("Результат не может быть меньше 1");
            }
            return DecimalToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static int RomanToDecimal(String roman) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        int result = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(roman.charAt(i));
            if (currentValue >= prevValue) {
                result += currentValue;
            } else {
                result -= currentValue;
            }
            prevValue = currentValue;
        }

        return result;
    }

    private static String DecimalToRoman(int number) {
        int[] decimalValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < decimalValues.length; i++) {
            while (number >= decimalValues[i]) {
                result.append(romanSymbols[i]);
                number -= decimalValues[i];
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        String result = calc(input);
        System.out.println(result);
    }
}