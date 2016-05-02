package ua.goit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SpecificActionProviderTest {

    private static Map<String, Action> actions;
    private static ActionProvider provider;

    @BeforeClass
    public static void setUp() {

        actions = new HashMap<>();
        provider = new SpecificActionProvider();
    }

    @Test
    public void testGetActions() throws Exception {

        Map<String, Action> map = provider.getActions();
        Map<String, Action> second = new HashMap<>();

        Assert.assertEquals(map,second);
    }
}