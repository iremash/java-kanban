package manager;

import java.util.ArrayList;
import java.util.List;

import model.Task;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>();
    private final static int MAX_SIZE = 10;

    @Override
    public void add(Task task) {
        if (history.size() >= MAX_SIZE) {
            history.remove(history.getFirst());
        }
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
