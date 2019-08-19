package com.hotmall.mathexpression;

import com.hotmall.mathexpression.ast.Expression;
import com.hotmall.mathexpression.parser.Parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, "utf-8");
        String expression = cin.nextLine();
        double result;
        Parser parser = new Parser();
        Expression expr = parser.parse(expression);
        if (expr != null) {
            result = expr.evaluate();
            System.out.println(result);
        } else {
            System.out.println("error");
        }
    }
}
