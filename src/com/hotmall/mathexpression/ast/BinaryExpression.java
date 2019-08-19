package com.hotmall.mathexpression.ast;

public class BinaryExpression extends Expression {

    private Expression left;
    private Expression right;
    private Operator op;

    public BinaryExpression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public double evaluate() {
        double d = 0;
        switch (op) {
            case PLUS:
                d = left.evaluate() + right.evaluate();
                break;
            case MINUS:
                d = left.evaluate() - right.evaluate();
                break;
            case MUL:
                d = left.evaluate() * right.evaluate();
                break;
            case DIV:
                d = left.evaluate() / right.evaluate();
                break;
            default:
        }
        return d;
    }
}
