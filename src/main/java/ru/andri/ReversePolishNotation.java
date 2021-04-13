package ru.andri;

import ru.andri.BracketMathLex.BRACKET_TYPE;
import ru.andri.MathLex.LEX_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {

    public static final String REG_EXP = "((?<=[-+*/()])|(?=[-+*/()]))";
    private static final String SPACE = " ";

    private final List<MathLex> origLexemes;
    private final Stack<MathLex> reversePolishLexemes = new Stack<>();

    private ReversePolishNotation(List<MathLex> origLexemes) {
        this.origLexemes = origLexemes;
        convertToReversePolish();
    }

    /**
     * Преобразование выражаения в набор лексем
     *
     * @param expression Выражение в инфиксной нотации
     */
    public static ReversePolishNotation parse(String expression) {
        List<MathLex> lexems = new ArrayList<>();
        for (String lex : expression.split(REG_EXP)) {
            lex = lex.stripLeading();
            lexems.add(stringToMathLex(lex));
        }
        return new ReversePolishNotation(lexems);
    }

    private static MathLex stringToMathLex(String lex) {
        if (lex.matches(TupleFunctionMathLex.tupleFuncRegExp)) {
            return new TupleFunctionMathLex(lex);
        } else if (lex.matches(BracketMathLex.bracketRegExp)) {
            return new BracketMathLex(lex);
        } else {
            return new NumberMathLex(lex);
        }
    }

    /**
     * Расчет результата выражения
     *
     * @return Результат
     */
    public double result() {
        Stack<Double> stack = new Stack<>();
        stack.push(Double.parseDouble("0"));

        for (MathLex mathLex : reversePolishLexemes) {
            if (mathLex.isType(LEX_TYPE.NUMBER)) {
                stack.push(((NumberMathLex) mathLex).getValue());
            } else if (stack.size() < 2) {
                throw new RuntimeException("Ошибка! Некорректное выражение");
            } else {
                stack.push(((TupleFunctionMathLex) mathLex).calc(stack.pop(), stack.pop()));
            }
        }
        return stack.pop();
    }

    /**
     * Конвертация в обратную польскую нотацию
     */
    private void convertToReversePolish() {
        Stack<MathLex> stack = new Stack<>();

        for (MathLex mathLex : origLexemes) {
            if (mathLex.isType(LEX_TYPE.NUMBER)) {
                reversePolishLexemes.push(mathLex);
            } else if (mathLex.isType(LEX_TYPE.BRACKET)) {
                BracketMathLex bracketMathLex = (BracketMathLex) mathLex;
                if (bracketMathLex.isBracketType(BRACKET_TYPE.OPEN_BRACKET)) {
                    stack.push(mathLex);
                } else {
                    while (true) {
                        MathLex t = stack.pop();
                        if (t.isType(LEX_TYPE.BRACKET) && ((BracketMathLex) t).isBracketType(BRACKET_TYPE.OPEN_BRACKET)) {
                            break;
                        }
                        reversePolishLexemes.push(t);
                        if (stack.isEmpty()) {
                            throw new RuntimeException("Ошибка! В выражении либо неверно поставлен разделитель, либо не согласованы скобки");
                        }
                    }
                }
            } else {
                while (true) {
                    if (stack.isEmpty() || stack.peek().isType(LEX_TYPE.BRACKET) || ((TupleFunctionMathLex) stack.peek()).compareTo((TupleFunctionMathLex) mathLex) < 0) {
                        stack.push(mathLex);
                        break;
                    } else {
                        reversePolishLexemes.push(stack.pop());
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            reversePolishLexemes.push(stack.pop());
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (MathLex mathLex : reversePolishLexemes) {
            res.append(SPACE).append(mathLex);
        }
        return res.toString().stripLeading();
    }

}