package com.hotmall.mathexpression.parser;

import com.hotmall.mathexpression.ast.*;

import java.util.ArrayList;

public class Parser {

    private Token currentToken = null;
    private ArrayList<Token> tokens = null;
    private int tokenIndex = 0;

    public Parser() {
    }

    public Expression parse(String expression) {

        try {
            tokens = new LexicalAnalyzer(expression).analyze();
            if (LexicalAnalyzer.isValid(tokens)) {
                currentToken = getToken();
                if (currentToken == null) {
                    return null;
                }
                return expr();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private Expression expr() {
        Token old;
        Expression expression = term();
        if (currentToken == null) {
            return expression;
        }

        while (currentToken.type() == TokenType.PLUS || currentToken.type() == TokenType.SUB) {
            old = currentToken;
            currentToken = getToken();
            Expression e1 = expr();
            expression = new BinaryExpression(expression, e1, old.type() == TokenType.PLUS ? Operator.PLUS : Operator.MINUS);
            if (currentToken == null) {
                break;
            }
        }
        return expression;
    }

    private Expression term() {
        Token old;
        Expression expression = factor();
        if (currentToken == null) {
            return expression;
        }
        while (currentToken.type() == TokenType.MUL || currentToken.type() == TokenType.DIV) {
            old = currentToken;
            currentToken = getToken();
            Expression e1 = term();
            expression = new BinaryExpression(expression, e1, old.type() == TokenType.MUL ? Operator.MUL : Operator.DIV);
            if (currentToken == null) {
                break;
            }
        }
        return expression;
    }

    private Expression factor() {
        Expression expression = null;

        switch (currentToken.type()) {
            case DOUBLE:
                expression = new NumericExpression(currentToken.value());
                currentToken = getToken();
                break;
            case MIN: {
                currentToken = getToken();
                ArrayList<Expression> exprList = new ArrayList<>();
                while (currentToken.type() != TokenType.CPAREN) {
                    if (currentToken.type() == TokenType.OPAREN) {
                        currentToken = getToken();
                        continue;
                    }
                    expression = expr();
                    if (expression != null) {
                        exprList.add(expression);
                    }
                }
                expression = new MinExpression(exprList);
                currentToken = getToken();
                break;
            }
            case MAX: {
                currentToken = getToken();
                ArrayList<Expression> exprList = new ArrayList<>();
                while (currentToken.type() != TokenType.CPAREN) {
                    if (currentToken.type() == TokenType.OPAREN) {
                        currentToken = getToken();
                        continue;
                    }

                    expression = expr();
                    if (expression != null) {
                        exprList.add(expression);
                    }
                }
                expression = new MaxExpression(exprList);
                currentToken = getToken();
                break;
            }
            case OPAREN: {
                currentToken = getToken();
                expression = expr();
                if (currentToken.type() != TokenType.CPAREN) {
                   return null;
                }
                currentToken = getToken();
                break;
            }
            default:
                break;
        }
        return expression;
    }

    private Token getToken() {
        if (tokenIndex > tokens.size() - 1) {
            return null;
        }
        Token token = tokens.get(tokenIndex);
        tokenIndex++;
        return token;
    }
}
