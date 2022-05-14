package ProgrammingLenguage;

import java.util.List;

public class Main {
    public static void main(String[] args){
        final String input1 = "4 + 4";
        final String input2 = "(2 + 7) * 3";
        final List<Token> tokens = new Lexer(input2).tokenize();

        for(Token token: tokens){
            System.out.println(token);
        }

        final List<Expression> expressions = new Parser(tokens).parse();
        for(Expression expr: expressions){
            System.out.println(expr + " = " + expr.eval());
        }
    }
}
