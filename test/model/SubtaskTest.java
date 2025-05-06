package model;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    Managers managers = new Managers();
    TaskManager taskManager = managers.getDefault();

    @Test
    void sameSubtaskShouldHaveSameIdIfTheyInSameEpic() {
        Epic epic1 = new Epic("Test addNewTask", "Test addNewTask description");
        Epic epic2 = new Epic("Test addTask", "Test addNewTask description");
        Subtask subtask1 = new Subtask("Test addNewTask", "Test addNewTask description", epic1);
        Subtask subtask2 = new Subtask("Test addNewTask", "Test addNewTask description", epic1);
        Subtask subtask3 = new Subtask("Test addNewTask", "Test addNewTask description", epic2);
        Subtask subtask4 = new Subtask("Test addTask", "Test addNewTask description", epic1);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask4);
        final int subtask1Id = subtask1.getId();
        final int subtask2Id = subtask2.getId();
        final int subtask3Id = subtask3.getId();
        final int subtask4Id = subtask4.getId();

        assertEquals(subtask1Id, subtask2Id, "Задачи не совпадают.");
        assertNotEquals(subtask1Id, subtask3Id, "Задачи совпадают.");
        assertNotEquals(subtask1Id, subtask4Id, "Задачи совпадают.");
    }

}