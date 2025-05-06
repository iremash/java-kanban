package manager;

public final class Managers {
    public TaskManager getDefault() {
        TaskManager manager = new InMemoryTaskManager();
        return manager;
    }

    public HistoryManager getDefaultHistory() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        return historyManager;
    }
}
