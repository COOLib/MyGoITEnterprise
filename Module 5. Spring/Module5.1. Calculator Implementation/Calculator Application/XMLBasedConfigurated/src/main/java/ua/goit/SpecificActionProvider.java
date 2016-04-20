package ua.goit;

import java.util.HashMap;
import java.util.Map;

public class SpecificActionProvider implements ActionProvider {

    private Map<String, Action> actions = new HashMap<>();

    public void init() {

        actions.put("+", new AddingAction());
        actions.put("-", new SubtractionAction());
        actions.put("*", new MultiplyingAction());
        actions.put("/", new DivisionAction());
        actions.put("sqrt", new SquareRootAction());
        actions.put("pow", new PowerAction());
        actions.put("!", new FactorialAction());
        actions.put("log", new LogarithmAction());
    }

    @Override
    public Map<String, Action> getActions() {
        return actions;
    }
}
