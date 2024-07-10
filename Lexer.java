/*
 * program to process lexemes into tokens to form a token list data structure
 * to give as input to the parser for the shank interpreter
 */
import java.util.ArrayList;

public class Lexer {
    /* globals */
    String line;
    String lexeme;
    int inputIndex; // measure where we are on the input line
    int tokenIndex;
    int inputLength;
    int lineNumber;
    ArrayList<Token> tokenList = new ArrayList<>(); 

    public void startState(String line) {
        lexeme = "";
        // handle EOL
        if (inputIndex >= inputLength) {
            Token token = new Token(Token.TokenType.ENDOFLINE);
            tokenList.add(token);
            tokenIndex++;
            return;
        }
        while (inputIndex < inputLength && Character.isWhitespace(line.charAt(inputIndex))) {
            inputIndex++;
        }
        if (inputIndex < inputLength && line.charAt(inputIndex) == '\n') {
            Token token = new Token(Token.TokenType.ENDOFLINE);
            tokenList.add(token);
            tokenIndex++;
            inputIndex++;
            return;
        }
        if (inputIndex < inputLength && Character.isLetter(line.charAt(inputIndex))) {
            identifierState(line);
        }
        else if (inputIndex < inputLength && Character.isDigit(line.charAt(inputIndex))) {
            numberState(line);
        }
        else {
            startState(line);
        }
    }

    public void identifierState(String line) {
        // handle whitespace at end
        while (inputIndex < inputLength && (Character.isWhitespace(line.charAt(inputIndex)) || line.charAt(inputIndex) == '\n')) {
            inputIndex++;
        }
        while (inputIndex < inputLength && Character.isLetterOrDigit(line.charAt(inputIndex))) {
            lexeme += line.charAt(inputIndex);
            inputIndex++;
        }
        Token token = new Token(Token.TokenType.IDENTIFIER, lexeme);
        token.setLineNumber(lineNumber);
        tokenList.add(token);
        tokenIndex++;
        startState(line);
    }

    public void numberState(String line) {
        while (inputIndex < inputLength && (Character.isWhitespace(line.charAt(inputIndex)) || line.charAt(inputIndex) == '\n')) {
            inputIndex++;
        }
        while (inputIndex < inputLength && Character.isDigit(line.charAt(inputIndex))) {
            lexeme += line.charAt(inputIndex);
            inputIndex++;
        }
        if (inputIndex < inputLength && line.charAt(inputIndex) == '.') {
            decimalState(line);
        } else {
            Token token = new Token(Token.TokenType.NUMBER, lexeme);
            tokenList.add(token);
            tokenIndex++;
            startState(line);
        }
    }

    public void decimalState(String line) {
        while (inputIndex < inputLength && (Character.isWhitespace(line.charAt(inputIndex)) || line.charAt(inputIndex) == '\n')) {
            inputIndex++;
        }
        lexeme += line.charAt(inputIndex);
        inputIndex++;
        while (inputIndex < inputLength && Character.isDigit(line.charAt(inputIndex))) {
            lexeme += line.charAt(inputIndex);
            inputIndex++;
        }
        Token token = new Token(Token.TokenType.NUMBER, lexeme);
        token.setLineNumber(lineNumber);
        tokenIndex++;
        tokenList.add(token);
        while (inputIndex < inputLength && (Character.isWhitespace(line.charAt(inputIndex)) || line.charAt(inputIndex) == '\n')) {
            inputIndex++;
        }
        startState(line);
    }

    /*
     * debug method to print tokens
     */
    public void printTokens() {
        for (Token token : tokenList) {
            System.out.println(token.toString());
        }
    }

    public void lex(String line) throws SyntaxErrorException{
        inputIndex = 0;
        inputLength = line.length();
        startState(line);
        lineNumber++;
    }
}
