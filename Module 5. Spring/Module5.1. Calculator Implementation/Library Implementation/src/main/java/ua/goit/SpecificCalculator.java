package ua.goit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Too use this way of solving your formula you have to write it as reverse polish notation.
 * Here is some description of reverse polish notation:
 * https://en.wikipedia.org/wiki/Reverse_Polish_notation
 * https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C
 */

public class SpecificCalculator implements Calculator {

    private Map<String, Action> actionMap;


    public SpecificCalculator(Map<String, Action> actionMap) {

        this.actionMap = actionMap;
    }

    public String getAnswer(String formula) {

        double result = calculateAnswer(formula);
        long cutted = Math.round(result);
        double accuracy = 0.00001;

        if ((result - cutted) > accuracy) {
            return String.valueOf(result);
        } else if (cutted <= Integer.MAX_VALUE) {
            return String.valueOf((int) result);
        } else {
            return String.valueOf((long) result);
        }
    }

    public double calculateAnswer(String formula) {

        StringBuilder sb = new StringBuilder(formula);
        String[] totalArr = formula.split(",");
        String[] arr = new String[totalArr.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = totalArr[arr.length - 1 - i];
        }

        List<String> myStrings = new ArrayList<>();
        int j = 0;

        for (int i = 0; i < arr.length; i++) {

            if (actionMap.containsKey(arr[i])) {

                if (actionMap.get(arr[i]).isUnary() == false) {

                    double first = Double.parseDouble(myStrings.get(j - 2));
                    double second = Double.parseDouble(myStrings.get(j - 1));

                    myStrings.remove(j - 1);
                    j--;
                    myStrings.remove(j - 1);
                    j--;

                    double result = actionMap.get(arr[i]).binaryAction(first, second);

                    myStrings.add(String.valueOf(result));
                    j++;
                } else {

                    double alone = Double.parseDouble(myStrings.get(j - 1));
                    double result = actionMap.get(arr[i]).unaryAction(alone);

                    myStrings.remove(j - 1);
                    myStrings.add(String.valueOf(result));
                    j++;
                }
            } else {

                myStrings.add(arr[i]);
                j++;
            }
        }

        return Double.parseDouble(myStrings.get(0));
    }

    public void setAction(String selectedAction, Action newAction) {

        actionMap.put(selectedAction, newAction);
    }

    public Action getAction(String selectedAction) {

        return actionMap.get(selectedAction);
    }
}
