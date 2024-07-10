public class SyntaxErrorException extends Exception {
    public SyntaxErrorException(Token inputToken) {
        Token token = new Token(Token.TokenType.ENDOFLINE);
        token = inputToken;
        System.out.printf("Line Number: %d / Token type: %s / Token value: %s, \n", token.getLineNumber(), token.getToken().toString(), token.getTokenValue());
    }
}
