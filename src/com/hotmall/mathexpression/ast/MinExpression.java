package com.hotmall.mathexpression.ast;

import java.util.ArrayList;

public class MinExpression extends Expression {

    private ArrayList<Expression> exprList;

    public MinExpression(ArrayList<Expression> exprList) {
        this.exprList = exprList;
    }

    @Override
    public double evaluate() {
        boolean isFirst = true;
        double minValue = 0;
        for (Expression expr : this.exprList) {
            double r = expr.evaluate();
            if (isFirst) {
                minValue = r;
                isFirst = false;
            } else {
                if (r < minValue) {
                    minValue = r;
                }
            }
        }
        return minValue;
    }
}
