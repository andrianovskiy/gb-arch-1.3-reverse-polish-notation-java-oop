package ru.andri.calculator.mathlex;

import java.math.BigDecimal;

public class NumberMathLex implements MathLex {

    private static final LEX_TYPE lexType = LEX_TYPE.NUMBER;
    private final Double value;

    public NumberMathLex(String lex) {
        this.value = Double.parseDouble(lex);
    }

    @Override
    public LEX_TYPE getType() {
        return lexType;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return BigDecimal.valueOf(value).stripTrailingZeros().toString();
    }

}
