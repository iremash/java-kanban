package model;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Managers managers = new Managers();
    TaskManager taskManager = managers.getDefault();

    @Test
    void sameTasksShouldHaveSameId() {
        Task task1 = new Task("Test addNewTask", "Test addNewTask description");
        Task task2 = new Task("Test addNewTask", "Test addNewTask description");
        Task task3 = new Task("Test addTask", "Test addNewTask description");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        final int task1Id = task1.getId();
        final int task2Id = task2.getId();
        final int task3Id = task3.getId();

        assertEquals(task1Id, task2Id, "Задачи не совпадают.");
        assertNotEquals(task1Id, task3Id, "Задачи совпадают.");
    }

    @Test
    void shouldNotChangeAfterAddingToTaskManager() {
        Task task1 = new Task("Test addNewTask", "Test addNewTask description", TaskStatus.NEW);
        Task task2 = new Task("Test addNewTask", "Test addNewTask description", TaskStatus.NEW);
        taskManager.addTask(task1);
        assertEquals(task1.getName(), task2.getName(), "Имя изменилось");
        assertEquals(task1.getDetails(), task2.getDetails(), "Детали задачи изменились");
        assertEquals(task1.getStatus(), task2.getStatus(), "Статус изменился");
    }
}