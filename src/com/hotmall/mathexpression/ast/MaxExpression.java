package com.hotmall.mathexpression.ast;

import java.util.ArrayList;

public class MaxExpression extends Expression {

    private ArrayList<Expression> exprList;

    public MaxExpression(ArrayList<Expression> exprList) {
        this.exprList = exprList;
    }

    @Override
    public double evaluate() {
        boolean isFirst = true;
        double maxValue = 0;
        for (Expression expr : this.exprList) {
            double r = expr.evaluate();
            if (isFirst) {
                maxValue = r;
                isFirst = false;
            } else {
                if (r > maxValue) {
                    maxValue = r;
                }
            }
        }
        return maxValue;
    }
}
