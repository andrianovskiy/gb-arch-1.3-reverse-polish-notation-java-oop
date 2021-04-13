package ru.andri;

public class BracketMathLex implements MathLex {

    public static final String bracketRegExp = "[()]";

    private static final String SYMBOL_OPEN_BRACKET = "(";
    private static final String SYMBOL_CLOSE_BRACKET = ")";

    private static final LEX_TYPE lexType = LEX_TYPE.BRACKET;
    private final BRACKET_TYPE bracketType;

    enum BRACKET_TYPE {
        OPEN_BRACKET(SYMBOL_OPEN_BRACKET), CLOSE_BRACKET(SYMBOL_CLOSE_BRACKET);

        public final String symbol;

        BRACKET_TYPE(String symbol) {
            this.symbol = symbol;
        }
    }

    public BracketMathLex(String lex) {
        switch (lex) {
            case SYMBOL_OPEN_BRACKET:
                this.bracketType = BRACKET_TYPE.OPEN_BRACKET;
                break;
            case SYMBOL_CLOSE_BRACKET:
                this.bracketType = BRACKET_TYPE.CLOSE_BRACKET;
                break;
            default:
                throw new IllegalArgumentException("Ошибка! Некорректный оператор " + lex);
        }
    }

    @Override
    public LEX_TYPE getType() {
        return lexType;
    }

    public boolean isBracketType(BRACKET_TYPE bracketType) {
        return this.bracketType.compareTo(bracketType) == 0;
    }

    @Override
    public String toString() {
        return bracketType.symbol;
    }

}
