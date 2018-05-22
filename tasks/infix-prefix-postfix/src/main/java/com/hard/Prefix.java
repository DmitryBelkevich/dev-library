package com.hard;

import java.util.Stack;

public class Prefix {
    private char separator;

    public Prefix() {
        this.separator = ' ';
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public double evaluate(String expression) {
        Stack<Double> operands = new Stack<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
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

                    stringBuilder.insert(0, currentChar);

                    i--;
                }

                double operand = Double.valueOf(stringBuilder.toString());

                operands.push(operand);
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^') {
                double operand1 = operands.pop();
                double operand2 = operands.pop();

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
                    case '^':
                        operands.push(Math.pow(operand1, operand2));
                        break;
                }
            }
        }

        return operands.pop();
    }
}
