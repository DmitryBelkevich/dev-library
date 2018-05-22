package com.hard;

import java.util.Stack;

public class Infix {
    public double evaluate(String infix) {
        // TODO
        return 0;
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
                character == '^';
    }
}
