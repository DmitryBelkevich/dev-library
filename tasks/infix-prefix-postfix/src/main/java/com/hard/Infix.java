package com.hard;

import java.util.Stack;

public class Infix {
    private char separator;

    public Infix() {
        this.separator = ' ';
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public String toPostfix(String infix) {
        Stack<Character> operators = new Stack<>();

        StringBuilder postfixBuilder = new StringBuilder();
        boolean first = true;

        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);

            if (currentChar == separator)
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

                postfixBuilder.append(separator);
                postfixBuilder.append(digitBuilder);
            } else if (isOperator(currentChar)) {
                operators.push(currentChar);
            }
        }

        while (!operators.empty()) {
            postfixBuilder.append(separator);

            char operator = operators.pop();
            postfixBuilder.append(operator);
        }

        return postfixBuilder.toString();
    }

    public String toPrefix(String infix) {
        return null;
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
