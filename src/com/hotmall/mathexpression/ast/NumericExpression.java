package com.hotmall.mathexpression.ast;

public class NumericExpression extends Expression {

    private double value;

    public NumericExpression(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public double evaluate() {
        return this.value;
    }
}
