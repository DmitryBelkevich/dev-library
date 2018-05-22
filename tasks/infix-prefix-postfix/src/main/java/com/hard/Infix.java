package com.hard;

import java.util.Stack;

public class Infix {
    public double evaluate(String infix) {
        double result = 0;

        for (int i = infix.length() - 1; i >= 0; i--) {
            char currentChar = infix.charAt(i);

            if (isSeparator(currentChar))
                continue;
            else if (isOperand(currentChar)) {
                StringBuilder digitBuilder = new StringBuilder();

                while (true) {
                    if (i < 0)
                        break;

                    currentChar = infix.charAt(i);

                    if (!isOperand(currentChar)) {
                        i++;
                        break;
                    }

                    digitBuilder.insert(0, currentChar);

                    i--;
                }

                double operand = Double.valueOf(digitBuilder.toString());
            } else if (isOperator(currentChar)) {
                String result1 = infix.substring(0, i);
                double operand1 = evaluate(result1);

                String result2 = infix.substring(i + 1, infix.length());
                double operand2 = Double.valueOf(result2);

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
            }
        }

        return result;
    }

    public String toPostfix(String infix) {//TODO brackets
        Stack<Character> operators = new Stack<>();

        StringBuilder postfixBuilder = new StringBuilder();
        boolean first = true;

        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);

            if (isSeparator(currentChar))
                continue;
            else if (isOperand(currentChar)) {
                StringBuilder digitBuilder = new StringBuilder();

                while (true) {
                    if (i >= infix.length())
                        break;

                    currentChar = infix.charAt(i);

                    if (!isOperand(currentChar)) {
                        i--;
                        break;
                    }

                    digitBuilder.append(currentChar);

                    i++;
                }

                if (first) {
                    first = false;
                    postfixBuilder.append(digitBuilder);
                    continue;
                }

                postfixBuilder.append(' ');
                postfixBuilder.append(digitBuilder);
            } else if (isOperator(currentChar)) {
                while (!operators.isEmpty() && currentChar != '(' && hasHigherPrecedence(operators.peek(), currentChar)) {
                    postfixBuilder.append(' ');
                    char operator = operators.pop();
                    postfixBuilder.append(operator);
                }

                operators.push(currentChar);
            }
        }

        while (!operators.empty()) {
            postfixBuilder.append(' ');

            char operator = operators.pop();
            postfixBuilder.append(operator);
        }

        return postfixBuilder.toString();
    }

    public String toPrefix(String infix) {
        // TODO
        return null;
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
                character == '^' ||
                character == '(' ||
                character == ')'
                ;
    }

    private int precedence(char character) {
        if (character == '(' || character == ')')
            return 1;
        else if (character == '+' || character == '-')
            return 2;
        else if (character == '*' || character == '/')
            return 3;

        return 0;
    }

    private boolean hasHigherPrecedence(char character1, char character2) {
        return precedence(character1) >= precedence(character2);
    }
}
