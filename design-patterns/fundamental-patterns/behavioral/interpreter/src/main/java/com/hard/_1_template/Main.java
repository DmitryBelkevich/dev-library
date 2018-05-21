package com.hard._1_template;

public class Main {
    public static void main(String[] args) {
        Expression orExpression = getOrExpression();
        Expression andExpression = getAndExpression();

        System.out.println("str1 or str2 constrains 'aaa'? - " + orExpression.interpret("aaa"));
        System.out.println("str1 and str2 constrains 'aaa bbb'? - " + andExpression.interpret("aaa bbb"));
    }

    // Rule: str1 or str2 contains 'aaa'
    public static Expression getOrExpression() {
        Expression str1 = new TerminalExpression("aaa");
        Expression str2 = new TerminalExpression("bbb");

        return new OrExpression(str1, str2);
    }

    // Rule: str1 and str2 contains 'aaa bbb'
    public static Expression getAndExpression() {
        Expression str1 = new TerminalExpression("aaa");
        Expression str2 = new TerminalExpression("bbb");

        return new AndExpression(str1, str2);
    }
}

/**
 * Expression
 */

interface Expression {
    boolean interpret(String context);
}

/**
 * Concrete Expression
 */

class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data))
            return true;

        return false;
    }
}

abstract class NonTerminalExpression implements Expression {
    protected Expression expression1;
    protected Expression expression2;

    public NonTerminalExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
}

class OrExpression extends NonTerminalExpression {
    public OrExpression(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) || expression2.interpret(context);
    }
}

class AndExpression extends NonTerminalExpression {
    public AndExpression(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) && expression2.interpret(context);
    }
}
