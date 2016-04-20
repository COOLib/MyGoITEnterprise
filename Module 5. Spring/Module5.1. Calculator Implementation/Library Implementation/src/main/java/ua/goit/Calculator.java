package ua.goit;


/**
 * This interface can use any way of creation string of formula to make calculations.
 *
 */
public interface Calculator {

    public String getAnswer(String formula);

    public double calculateAnswer(String formula);

    public void setAction(String action, Action newAction);

    public Action getAction(String selectedAction);
}
