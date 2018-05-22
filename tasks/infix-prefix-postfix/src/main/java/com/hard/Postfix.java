package com.hard;

import java.util.Stack;

public class Postfix {
    public double evaluate(String expression) {
        Stack<Double> operands = new Stack<>();

        char separator = ' ';

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (currentChar == separator)
                continue;
            else if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder stringBuilder = new StringBuilder();

                while (true) {
                    currentChar = expression.charAt(i);

                    if (!Character.isDigit(currentChar))
                        if (currentChar != '.')
                            if (currentChar == separator)
                                break;

                    stringBuilder.append(currentChar);

                    i++;
                }

                operands.push(Double.valueOf(stringBuilder.toString()));
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                double operand2 = operands.pop();
                double operand1 = operands.pop();

                switch (currentChar) {
                    case '+':
                        operands.push(operand1 + operand2);
                        break;
                    case '-':
                        operands.push(operand1 - operand2);
                        break;
                    case '*':
                        operands.push(operand1 * operand2);
                        break;
                    case '/':
                        operands.push(operand1 / operand2);
                        break;
                }
            }
        }

        return operands.pop();
    }
}
