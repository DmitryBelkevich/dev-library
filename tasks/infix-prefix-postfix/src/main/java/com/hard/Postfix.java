package com.hard;

import java.util.Stack;

public class Postfix {
    private char separator;

    public Postfix() {
        this.separator = ' ';
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public double evaluate(String postfix) {
        Stack<Double> operands = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char currentChar = postfix.charAt(i);

            if (currentChar == separator)
                continue;
            else if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder digitBuilder = new StringBuilder();

                while (true) {
                    currentChar = postfix.charAt(i);

                    if (!Character.isDigit(currentChar))
                        if (currentChar != '.')
                            if (currentChar == separator)
                                break;

                    digitBuilder.append(currentChar);

                    i++;
                }

                double operand = Double.valueOf(digitBuilder.toString());

                operands.push(operand);
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^') {
                double operand2 = operands.pop();
                double operand1 = operands.pop();

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
}
