public class Token {
    private TokenType type;
    private String value;
    private int lineNumber;

    public enum TokenType { NUMBER, IDENTIFIER, UNTIL, STRINGLITERAL, TO, SQUAREROOT, NOT, INTEGER,
        ADD, SUBTRACT, FROM, ARRAY, COMMA, LESSTHAN, GETRANDOM, FOR, AND, STRING, CHARACTERLITERAL,
        DIVIDE, MULTIPLY, OF, SEMICOLON, COLON, GREATERTHAN, INTEGERTOREAL, BOOLEAN, UNTERMINATEDSTRING,
        MODULO, DEFINE, CONSTANTS, EQUAL, LESSTHANEQUALTO, REALTOINTEGER, REAL, INVALIDCHARACTER,
        IF, WHILE, VARIABLES, LPAREN, RPAREN, GREATERTHANEQUALTO, SUBSTRING, CHARACTER,
        REPEAT, ELSE, LBRACKET, RBRACKET, ELSIF, ASSIGNMENTEQUAL, READ, THEN,
        NOTEQUAL, INDENT, DEDENT, START, END, LEFT, RIGHT, WRITE, TRUE, FALSE, ENDOFLINE }

    public Token() {

    }

    // Accessor
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public void setToken(TokenType inputToken) {
        type = inputToken;
    }

    public void setLineNumber(int inputLineNumber) {
        lineNumber = inputLineNumber;
    }

    public void setTokenValue (String inputValue) {
        value = inputValue;
    }

    public TokenType getToken() {
        return type;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getTokenValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[TokenType: " + type + ", TokenValue: " + value + "]";
    }
}
