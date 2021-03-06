package ua.goit;


import ua.goit.actions.Action;

import java.math.BigDecimal;

/**
 * This interface can use any way of creation string of formula to make calculations.
 *
 */
public interface Calculator {

    public String getAnswer(String formula) throws Exception;

    public BigDecimal calculateAnswer(String formula) throws Exception;

    public void setAction(String action, Action newAction);

    public Action getAction(String selectedAction);

    public String convert(String formula);

    public String reverseReplace(String formula);
}
