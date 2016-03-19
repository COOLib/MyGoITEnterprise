import java.util.List;

public interface Executor<T> {

    void addTask(Task<? extends T> task) throws Exception;

    void addTask(Task<? extends T> task, Validator<? super T> validator) throws Exception;

    void execute();

    List<T> getValidResults() throws Exception;

    List<T> getInvalidResults() throws Exception;
}
