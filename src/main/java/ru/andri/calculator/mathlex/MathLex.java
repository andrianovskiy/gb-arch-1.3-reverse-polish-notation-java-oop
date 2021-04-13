package ru.andri.calculator.mathlex;

public interface MathLex {

    enum LEX_TYPE {
        TUPLE_FUNC, BRACKET, NUMBER
    }

    LEX_TYPE getType();

    default boolean isType(LEX_TYPE lexType) {
        return getType().compareTo(lexType) == 0;
    }

}
