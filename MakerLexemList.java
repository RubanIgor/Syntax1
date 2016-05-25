package Parser;

import java.util.ArrayList;
import java.util.List;

public final class MakerLexemList {

    private static final String OPERATOR_CHARS = "+-*/()";
    private static final LexemType[] OPERATOR_LEXEMS = {
        LexemType.Plus, 
        LexemType.Minus, 
        LexemType.Mult, 
        LexemType.Div,
        LexemType.LeftParenthesis,
        LexemType.RightParenthesis
    };
    
    private final String inputString;
    private final int lengthString;
    private final List<Lexem> lexems;
    private int pos;
    private char currentChar;

    public MakerLexemList(String inputString) {
        this.inputString = inputString;
        this.lengthString = inputString.length();
        this.lexems = new ArrayList<>();
    }
    
    public List<Lexem> makeList() {
        pos = -1;
        getNextChar();
        while (pos < lengthString) {
            if      (currentChar == ' ') getNextChar();
            else if (Character.isDigit(currentChar)) getNumber();
            else if (OPERATOR_CHARS.indexOf(currentChar) != -1) getOperator();
            else throw new RuntimeException("Неивестный символ: <" + currentChar + ">");
        }
        return lexems;
    }
    
    private void getNumber() {
        final StringBuilder buffer = new StringBuilder();
        while (Character.isDigit(currentChar)) {            
            buffer.append(currentChar);
            getNextChar();
        }
        addLexem(LexemType.Number, buffer.toString());
    }
    
    private void getOperator() {
        addLexem(OPERATOR_LEXEMS[OPERATOR_CHARS.indexOf(currentChar)]);
        getNextChar();
    }
    
    private void getNextChar() {
        pos++;
        currentChar = pos < lengthString ? inputString.charAt(pos) : '\0';
    }
    
    private void addLexem(LexemType lexemType, String lexemValue) {
        lexems.add(new Lexem(lexemType, lexemValue));
    }
    
    private void addLexem(LexemType lexemType) {
        addLexem(lexemType, "");
    }
    
}
