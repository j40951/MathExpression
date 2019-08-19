package com.hotmall.mathexpression.parser;

import java.util.ArrayList;
import java.util.Stack;

public class LexicalAnalyzer {

    private String expression;

    public LexicalAnalyzer(String expression) {
        this.expression = expression;
    }

    public ArrayList<Token> analyze() throws Exception {

        ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 0; i < this.expression.length(); i++) {
            char c = this.expression.charAt(i);

            switch (c) {
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    break;
                case '-':
                    tokens.add(new Token(TokenType.SUB, "-"));
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MUL, "*"));
                    break;
                case '/':
                    tokens.add(new Token(TokenType.DIV, "/"));
                    break;
                case '(':
                    tokens.add(new Token(TokenType.OPAREN, "("));
                    break;
                case ')':
                    tokens.add(new Token(TokenType.CPAREN, ")"));
                    break;
                case ',':
                    break;
                default:
                    StringBuffer sb = new StringBuffer();
                    if (Character.isLetter(c)) {
                        for (int j = i; j < this.expression.length(); j++) {
                            char c1 = this.expression.charAt(j);
                            if (!Character.isLetter(c1)) {
                                break;
                            }
                            sb.append(c1);
                        }

                        String method = sb.toString();
                        if (method.equalsIgnoreCase("MAX")) {
                            tokens.add(new Token(TokenType.MAX, method));
                        } else if (method.equalsIgnoreCase("MIN")) {
                            tokens.add(new Token(TokenType.MIN, method));
                        } else {
                            throw new Exception("Illegal expression");
                        }

                        i += method.length() - 1;
                    } else if (Character.isDigit(c)) {
                        int j = i;
                        while (j < this.expression.length()) {
                            char c1 = this.expression.charAt(j);
                            if (!Character.isDigit(c1) && c1 != '.') {
                                break;
                            }
                            sb.append(c1);
                            j++;
                        }
                        tokens.add(new Token(TokenType.DOUBLE, sb.toString()));
                        i = j - 1;
                    } else {
                        throw new Exception("Illegal expression");
                    }
            }
        }

        return tokens;
    }

    public static boolean isValid(ArrayList<Token> tokens) {
        if (tokens == null) {
            return false;
        }

        Stack<Token> s = new Stack<>();
        for (Token token : tokens) {
            if (token == null) {
                return false;
            }

            if (token.type() == TokenType.OPAREN) {
               s.push(token);
            } else if (token.type() == TokenType.CPAREN) {
                s.pop();
            }
        }
        return s.size() == 0;
    }
}
