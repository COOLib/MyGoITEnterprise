package ua.goit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Executor {

    private ActionProvider provider;

    public Executor(ActionProvider provider) {
        this.provider = provider;
    }

    private Logger logger = LoggerFactory.getLogger(Executor.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Executor executor = applicationContext.getBean("executor", Executor.class);

        executor.execute();
    }

    public void execute() {

        Map<String, Action> actionMap = new HashMap<>();

        Calculator calculator = new SpecificCalculator(actionMap);

        provider.getActions().forEach(calculator::setAction);

        System.out.println("Here you have to enter your formula at reverse polish notation view!");
        System.out.println("For example: -,+,*,5,8,10");
        System.out.println("Please, enter your formula!");

        Scanner scanner = new Scanner(System.in);
        String formula = scanner.nextLine();

        logger.info("SLF4J. Before executing of: " + logger.getClass().getName());

        String answer = calculator.getAnswer(formula);

        logger.info("SLF4J. After executing of: " + logger.getClass().getName());

        System.out.println("The solution of your formula is:");
        System.out.println(formula + " = " + answer);
    }
}
