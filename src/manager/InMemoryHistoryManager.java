package manager;

import java.util.ArrayList;

import model.Task;

public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (history.size() < 10) {
            history.add(task);
        } else {
            history.remove(history.getFirst());
            history.add((task));
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }
}
