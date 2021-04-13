package ru.andri;

import java.math.BigDecimal;

public class Main {

    private static final String testExpression = "(1+  2)*4+3";

    public static void main(String[] args) {
        String expression;
        if (args.length < 1) {
            expression = testExpression;
            System.out.println("Выражение не передано. Расчет будет произведен для тестового выражения");
        } else {
            System.out.println("Выражение прочитано из аргументов командной строки");
            expression = args[0];
        }
        System.out.println(expression);

        // Инициализируем класс
        ReversePolishNotation rpn = ReversePolishNotation.parse(expression);
        // Печатаем в обратной польской нотации
        System.out.println(rpn);
        // Рассчитываем результат и выводим в консоль
        System.out.println(BigDecimal.valueOf(rpn.result()).stripTrailingZeros());

    }
}