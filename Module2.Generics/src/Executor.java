import java.util.List;

public interface Executor<T> {

    void addTask(T task) throws Exception;

    void addTask(Rectangle task, Validator<Number> validator) throws Exception;

    void execute() throws Exception;

    List getValidResults() throws Exception;

    List getInvalidResults() throws Exception;
}
