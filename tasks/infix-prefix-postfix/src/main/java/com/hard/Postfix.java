package com.hard;

import java.util.Stack;

public class Postfix {
    public double evaluate(String expression) {
        Stack<Double> digits = new Stack<>();

        char delimeter = ' ';

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (currentChar == delimeter)
                continue;
            else if (Character.isDigit(currentChar) || currentChar == '.') {
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

                digits.push(Double.valueOf(stringBuilder.toString()));
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                double operand2 = digits.pop();
                double operand1 = digits.pop();

                switch (currentChar) {
                    case '+':
                        digits.push(operand1 + operand2);
                        break;
                    case '-':
                        digits.push(operand1 - operand2);
                        break;
                    case '*':
                        digits.push(operand1 * operand2);
                        break;
                    case '/':
                        digits.push(operand1 / operand2);
                        break;
                }
            }
        }

        return digits.pop();
    }
}
