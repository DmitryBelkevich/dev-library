package com.hard;

import java.util.Stack;

public class Postfix {
    public double evaluate(String postfix) {
        Stack<Double> operands = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char currentChar = postfix.charAt(i);

            if (isSeparator(currentChar))
                continue;
            else if (isOperand(currentChar)) {
                StringBuilder digitBuilder = new StringBuilder();

                while (true) {
                    if (i >= postfix.length())
                        break;

                    currentChar = postfix.charAt(i);

                    if (!isOperand(currentChar)) {
                        i--;
                        break;
                    }

                    digitBuilder.append(currentChar);

                    i++;
                }

                double operand = Double.valueOf(digitBuilder.toString());

                operands.push(operand);
            } else if (isOperator(currentChar)) {
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

    private boolean isSeparator(char character) {
        return character == ' ';
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
