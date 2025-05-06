package manager;

import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    Managers managers = new Managers();
    TaskManager taskManager = managers.getDefault();

    @Test
    void shouldAddDifferentTaskAndFindThemById() {
        Task task = new Task("name", "details");
        Epic epic = new Epic("name", "details");
        Subtask subtask = new Subtask("name", "details", epic);

        taskManager.addEpic(epic);
        taskManager.addTask(task);
        taskManager.addSubtask(subtask);

        assertEquals(subtask, taskManager.getById(subtask.getId()), "Задача не была добавлена");
        assertEquals(task, taskManager.getById(task.getId()), "Задача не была добавлена");
        assertEquals(epic, taskManager.getById(epic.getId()), "Задача не была добавлена");
    }

    @Test
    void tasksWithAuthomaticlyGeneratedAnInputIdNotConflictInManager() {
        Task task1 = new Task("name", "details");
        Task task2 = new Task("Taskname", "details");
        taskManager.addTask(task1);
        task2.setId(task1.getId());
        taskManager.addTask(task2);
        assertNotEquals(task1, task2, "У разных задач меняется id");
    }
}