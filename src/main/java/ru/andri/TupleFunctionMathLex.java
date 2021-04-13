package ru.andri;

public class TupleFunctionMathLex implements MathLex {

    public static final String tupleFuncRegExp = "[-+*/]";

    private static final String SYMBOL_ADD = "+";
    private static final String SYMBOL_SUBTRACT = "-";
    private static final String SYMBOL_MULTIPLY = "*";
    private static final String SYMBOL_DIVIDE = "/";

    private static final LEX_TYPE lexType = LEX_TYPE.TUPLE_FUNC;
    private final TYPLE_FUNC_TYPE tupleFuncType;

    enum TYPLE_FUNC_TYPE {
        ADD(SYMBOL_ADD, 1), SUBTRACT(SYMBOL_SUBTRACT, 1), MULTIPLY(SYMBOL_MULTIPLY, 2), DIVIDE(SYMBOL_DIVIDE, 2);

        public final String symbol;
        public final int priority;

        TYPLE_FUNC_TYPE(String symbol, int priority) {
            this.symbol = symbol;
            this.priority = priority;
        }
    }

    public TupleFunctionMathLex(String lex) {
        switch (lex) {
            case SYMBOL_ADD:
                this.tupleFuncType = TYPLE_FUNC_TYPE.ADD;
                break;
            case SYMBOL_SUBTRACT:
                this.tupleFuncType = TYPLE_FUNC_TYPE.SUBTRACT;
                break;
            case SYMBOL_MULTIPLY:
                this.tupleFuncType = TYPLE_FUNC_TYPE.MULTIPLY;
                break;
            case SYMBOL_DIVIDE:
                this.tupleFuncType = TYPLE_FUNC_TYPE.DIVIDE;
                break;
            default:
                throw new IllegalArgumentException("Ошибка! Некорректный оператор " + lex);
        }
    }

    @Override
    public LEX_TYPE getType() {
        return lexType;
    }

    public double calc(double dig1, double dig2) {
        switch (tupleFuncType) {
            case ADD:
                return dig2 + dig1;
            case SUBTRACT:
                return dig2 - dig1;
            case MULTIPLY:
                return dig2 * dig1;
            case DIVIDE:
                return dig2 / dig1;
            default:
                throw new RuntimeException();
        }
    }

    public int compareTo(TupleFunctionMathLex tupleFunc) {
        return Integer.compare(this.tupleFuncType.priority, tupleFunc.tupleFuncType.priority);
    }

    @Override
    public String toString() {
        return tupleFuncType.symbol;
    }

}
