package ua.goit;

public class Main {

    public static void main(String[] args) {

        ExecutorImpl numberExecutor = new ExecutorImpl();
        Validator validator = new NumberValidator();

        try {
            numberExecutor.addTask(new Rectangle(10, 20), validator);
            numberExecutor.addTask(new Rectangle(0, 0), validator);
            numberExecutor.addTask(new Rectangle(20, 30), validator);
            numberExecutor.addTask(new Rectangle(15, 15), validator);
            numberExecutor.addTask(new Rectangle(10, 8), validator);
            numberExecutor.addTask(new Circle(10), validator);
            numberExecutor.addTask(new Circle(7), validator);

        } catch (Exception e) {
            e.printStackTrace();
        }

        numberExecutor.execute();

        System.out.println("Valid results:");
        try {
            numberExecutor.getValidResults().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Invalid results:");
        try {
            numberExecutor.getInvalidResults().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
