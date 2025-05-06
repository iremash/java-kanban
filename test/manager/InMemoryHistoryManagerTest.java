package manager;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    Managers managers = new Managers();
    TaskManager taskManager = managers.getDefault();
    HistoryManager historyManager = managers.getDefaultHistory();

    @Test
    void shouldSaveOldVersionOfTasksAdded() {
        Task task = new Task("name", "details", TaskStatus.NEW);
        taskManager.addTask(task);
        taskManager.getById(task.getId());
        Task newTask = new Task("newName", "newDetails", TaskStatus.IN_PROGRESS);
        task = newTask;
        Task oldTask = taskManager.getHistory().getFirst();
        assertNotEquals(task.getName(), oldTask.getName());
        assertNotEquals(task.getDetails(), oldTask.getDetails());
        assertNotEquals(task.getStatus(), oldTask.getStatus());
    }
}