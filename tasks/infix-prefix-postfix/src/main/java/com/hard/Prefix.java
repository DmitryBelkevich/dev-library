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

    public double evaluate(String prefix) {
        Stack<Double> operands = new Stack<>();

        for (int i = prefix.length() - 1; i >= 0; i--) {
            char currentChar = prefix.charAt(i);

            if (currentChar == separator)
                continue;
            else if (isOperand(currentChar)) {
                StringBuilder digitBuilder = new StringBuilder();

                while (true) {
                    currentChar = prefix.charAt(i);

                    if (currentChar == separator)
                        if (!isOperand(currentChar))
                            break;

                    digitBuilder.insert(0, currentChar);

                    i--;
                }

                double operand = Double.valueOf(digitBuilder.toString());

                operands.push(operand);
            } else if (isOperator(currentChar)) {
                double operand1 = operands.pop();
                double operand2 = operands.pop();

                double result = 0;

                switch (currentChar) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        result = operand1 / operand2;
                        break;
                    case '^':
                        result = Math.pow(operand1, operand2);
                        break;
                }

                operands.push(result);
            }
        }

        return operands.pop();
    }

    private boolean isOperand(char character) {
        return Character.isDigit(character) ||
                character == '.';
    }

    private boolean isOperator(char character) {
        return character == '+' ||
                character == '-' ||
                character == '*' ||
                character == '/' ||
                character == '^';
    }
}
