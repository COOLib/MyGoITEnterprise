package ua.goit;

import ua.goit.actions.Action;

import java.util.*;


public class SpecificCalculator implements Calculator {

    private Map<String, Action> actionMap;


    public SpecificCalculator(Map<String, Action> actionMap) {

        this.actionMap = actionMap;
    }

    public String getAnswer(String formula) throws Exception {

        String postfixFormula = convert(formula);

        StringBuilder builder = new StringBuilder();
        String[] polishArr = postfixFormula.split(" ");

        for (int i = polishArr.length - 1; i >= 0; i--) {
            builder.append(polishArr[i]);
            builder.append(" ");
        }

        String totalFormula = reverseReplace(builder.toString());
        double result = calculateAnswer(totalFormula);
        long cutted = (long) result;
        double accuracy = 0.00001;

        if (Math.abs(result) - Math.abs(cutted) > accuracy) {
            return String.valueOf(result);
        } else if (cutted <= Integer.MAX_VALUE && cutted >= Integer.MIN_VALUE) {
            return String.valueOf((int) result);
        } else {
            return String.valueOf((long) result);
        }
    }

    public String convert(String formula) {

        StringBuilder sb = new StringBuilder();
        String replacedFormula = replace(formula);

        Deque<Character> op = new ArrayDeque<>();
        char[] chars = replacedFormula.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (ch == '.') {
                if (i == 0 || Character.isDigit(chars[i - 1]) || isOperator(chars[i - 1]) || chars[i - 1] == ' ') {
                    sb.append(ch);
                }
            } else if (ch == 'e' && i > 0 && (chars[i - 1] == '.' || Character.isDigit(chars[i - 1]))) {
                sb.append(ch);

            } else if (Character.isDigit(ch)) {
                if (i + 1 < chars.length && (Character.isDigit(chars[i + 1]) || chars[i + 1] == '.')) {
                    sb.append(ch);
                } else {
                    sb.append(ch);
                    sb.append(' ');
                }
            } else if (ch == '(') {

                op.push(ch);
            } else if (ch == ')') {

                while (op.peek() != '(') {
                    sb.append(op.poll());
                    sb.append(' ');
                }

                op.pop();
            } else if (isOperator(ch)) {

                if (i == 0 || ((chars[i - 1] == '(' || chars[i - 1] == 'e'))) {

                    if (ch == '-') {
                        sb.append(ch);
                    }
                } else {

                    while (!op.isEmpty() && isOperator(op.peek()) && priority(op.peek()) >= priority(ch)) {
                        sb.append(op.pop()).append(' ');
                    }
                    op.push(ch);
                }
            }
        }

        while (!op.isEmpty()) {
            sb.append(op.pop());
            sb.append(' ');
        }

        return sb.toString();
    }

    public String replace(String str) {

        String s = "pow", s1 = "sqrt", s2 = "log", s3 = "tan";
        String result = str;

        int k = str.length();
        while (k >= 0) {
            if (result.contains(s)) {
                result = result.replace(s, "P");
            } else if (result.contains(s1)) {
                result = result.replace(s1, "S");
            } else if (result.contains(s2)) {
                result = result.replace(s2, "L");
            } else if (result.contains(s3)) {
                result = result.replace(s3, "T");
            }

            k--;
        }

        return result;
    }

    public String reverseReplace(String str) {

        String s = "P", s1 = "S", s2 = "L", s3 = "T";
        String result = str;

        int k = str.length();
        while (k >= 0) {
            if (result.contains(s)) {
                result = result.replace(s, "pow");
            } else if (result.contains(s1)) {
                result = result.replace(s1, "sqrt");
            } else if (result.contains(s2)) {
                result = result.replace(s2, "log");
            } else if (result.contains(s3)) {
                result = result.replace(s3, "tan");
            }

            k--;
        }

        return result;
    }

    private boolean isOperator(char ch) {
        return ch == 'P' || ch == 'S' || ch == '!' || ch == 'L' || ch == 'T' || ch == '/' || ch == '*' || ch == '+' || ch == '-';
    }

    private int priority(char operator) {
        switch (operator) {
            case 'S':
            case '!':
            case 'P':
            case 'L':
            case 'T':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return 0;
    }

    public double calculateAnswer(String formula) throws Exception {

        if (!isRight(formula)) {
            throw new Exception("Entered formula is not correct. Please, check quantity of opening and closing brackets!!");
        }

        String[] totalArr = formula.split(" ");
        String[] arr = new String[totalArr.length];

        Doubles doubles = new Doubles();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = totalArr[arr.length - 1 - i];
        }

        List<String> myStrings = new ArrayList<>();
        int j = 0;

        for (String anArr : arr) {

            if (actionMap.containsKey(anArr)) {

                if (!actionMap.get(anArr).isUnary()) {

                    double first = doubles.parse(myStrings.get(j - 2));

                    double second = doubles.parse(myStrings.get(j - 1));

                    myStrings.remove(j - 1);
                    j--;
                    myStrings.remove(j - 1);
                    j--;

                    double result = actionMap.get(anArr).binaryAction(first, second);

                    myStrings.add(String.valueOf(result));
                    j++;
                } else {

                    double alone = doubles.parse(myStrings.get(j - 1));
                    double result = actionMap.get(anArr).unaryAction(alone);

                    myStrings.remove(j - 1);
                    myStrings.add(String.valueOf(result));
                }
            } else {

                myStrings.add(anArr);
                j++;
            }
        }

        return Double.parseDouble(myStrings.get(0));
    }

    private boolean isRight(String formula) {

        int k = 0;
        int l = 0;

        for (char ch : formula.toCharArray()) {
            if (ch == '(') {
                k++;
            }
            if (ch == ')') {
                l++;
            }
        }

        return k == l;
    }

    public void setAction(String selectedAction, Action newAction) {

        actionMap.put(selectedAction, newAction);
    }

    public Action getAction(String selectedAction) {

        return actionMap.get(selectedAction);
    }
}
