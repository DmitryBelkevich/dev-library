package com.hard._1_example;

public class Main {
    public static void main(String[] args) {
        Expression orExpression = getOrExpression();
        Expression andExpression = getAndExpression();

        System.out.println("str1 or str2 constrains 'aaa'? - " + orExpression.interpret("... aaa ..."));
        System.out.println("str1 and str2 constrains 'aaa bbb'? - " + andExpression.interpret("... aaa ... bbb ..."));
    }

    // Rule: str1 or str2 contains 'aaa'
    public static NonTerminalExpression getOrExpression() {
        TerminalExpression expression1 = new ContainsExpression("aaa");
        TerminalExpression expression2 = new ContainsExpression("bbb");

        NonTerminalExpression expression = new OrExpression(expression1, expression2);

        return expression;
    }

    // Rule: str1 and str2 contains 'aaa bbb'
    public static NonTerminalExpression getAndExpression() {
        TerminalExpression expression1 = new ContainsExpression("aaa");
        TerminalExpression expression2 = new ContainsExpression("bbb");

        NonTerminalExpression expression = new AndExpression(expression1, expression2);

        return expression;
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

abstract class TerminalExpression implements Expression {
    protected String data;

    public TerminalExpression(String data) {
        this.data = data;
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

class ContainsExpression extends TerminalExpression {
    public ContainsExpression(String data) {
        super(data);
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data))
            return true;

        return false;
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
