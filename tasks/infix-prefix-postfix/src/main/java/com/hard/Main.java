package com.hard;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String expression = "2.1 2.2 + 2.3 *";
        double result = evaluate(expression);
        System.out.println(result);
    }

    private static double evaluate(String expression) {
        Stack<String> stack = new Stack<>();

        char delimeter = ' ';

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (currentChar == delimeter)
                continue;
            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder stringBuilder = new StringBuilder();
                int j = i;
                while (true) {
                     if (!Character.isDigit(expression.charAt(j)))
                         if (expression.charAt(j) != '.')
                             if (expression.charAt(j) == delimeter)
                                 break;

                    stringBuilder.append(expression.charAt(j));

                    j++;
                }

                i = j;

                stack.push(stringBuilder.toString());
            }
        }

        System.out.println(stack);

        return 0;
    }
}
