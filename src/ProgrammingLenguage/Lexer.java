package ProgrammingLenguage;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private  static final String OPERATOR_CAHRS = "+-*/()";
    private static final TokenType[] OPERATOR_TOKENS = {
            TokenType.PLUS, TokenType.MINUS,
            TokenType.STAR, TokenType.SLASH,
            TokenType.LPAREN, TokenType.RPAREN,
    };
    private final String input;
    private final int length;
    private List<Token> tokens;
    private int pos;

    public Lexer(String input){
        this.input = input;
        length = input.length();

        tokens = new ArrayList<>();
    }

    public List<Token> tokenize(){
        while(pos < length){
            final char current = peek(0);
            if(Character.isDigit(current)) tokenizeNumber();
            else if(OPERATOR_CAHRS.indexOf(current) != -1){
                tokenizeOperator();
            } else{
                next();
            }
        }
        return tokens;
    }

    private void tokenizeOperator() {
        final int position = OPERATOR_CAHRS.indexOf(peek(0));
        addToken(OPERATOR_TOKENS[position]);
        next();
    }

    private void tokenizeNumber(){
        char current = peek(0);
        final StringBuilder buffer = new StringBuilder();
        while (Character.isDigit(current)){
            buffer.append(current);
            current = next();
        }
        addToken(TokenType.NUMBER, buffer.toString());
    }

    private  char next(){
        pos++;
        return peek(0);
    }

    private  char peek(int relativePosition){
        final int position = pos + relativePosition;
        if(position >= length) return '\0';
        return input.charAt(position);
    }

    private void addToken(TokenType type){
        addToken(type, "");
    }

    private void addToken(TokenType type, String text){
        tokens.add(new Token(type, text));
    }
}
