package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */



    public String evaluate(String statement) {

        Stack<Character> operations = new Stack<Character>();
        Stack<Double> numbers = new Stack<Double>();

        try {
            for (int i = 0; i < statement.length(); i++) {

                char currentCharacter = statement.charAt(i);

                if (Character.isDigit(currentCharacter)) {
                    String number = "";
                    while (i < statement.length() && (Character.isDigit(statement.charAt(i)) || statement.charAt(i) == '.')) {
                        number += statement.charAt(i++);
                    }
                    i--;
                    numbers.add(Double.parseDouble(number));
                } else if (currentCharacter == '(') {
                    operations.add(currentCharacter);
                } else if (currentCharacter == ')') {
                    while (operations.peek() != '(') {
                        numbers.add(localEvaluate(numbers, operations.pop()));
                    }
                    operations.pop();
                } else if (isOperation(currentCharacter)) {
                    while (!operations.isEmpty() && operationPriority(operations.peek()) >= operationPriority(currentCharacter))
                        numbers.add(localEvaluate(numbers, operations.pop()));
                    operations.add(currentCharacter);
                } else {
                    return null;
                }
            }

            while (!operations.isEmpty()) {
                numbers.add(localEvaluate(numbers, operations.pop()));
            }

            DecimalFormat df = new DecimalFormat("#.####");
            return df.format(numbers.pop()).replace(',', '.');

        } catch (Exception e) {
            return null;
        }

    }


    private static int operationPriority(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private boolean isOperation(char currentCharacter) {
        return (currentCharacter == '+' || currentCharacter == '-' || currentCharacter == '*' || currentCharacter == '/');
    }

    private Double localEvaluate(Stack<Double> numbers, char operation) {

        double secondNumber = numbers.pop();
        double firstNumber = numbers.pop();
        switch (operation) {
            case '+':
                return (firstNumber + secondNumber);
            case '-':
                return (firstNumber - secondNumber);
            case '*':
                return (firstNumber * secondNumber);
            case '/':
                if (secondNumber == 0.0) {
                    return (null);
                }
                return (firstNumber / secondNumber);
        }
        return null;
    }
}