package model;

import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    Managers managers = new Managers();
    TaskManager taskManager = managers.getDefault();

    @Test
    void sameEpicsShouldHaveSameId() {
        Epic epic1 = new Epic("Test addNewTask", "Test addNewTask description");
        Epic epic2 = new Epic("Test addNewTask", "Test addNewTask description");
        Epic epic3 = new Epic("Test addTask", "Test addNewTask description");
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addEpic(epic3);
        final int epic1Id = epic1.getId();
        final int epic2Id = epic2.getId();
        final int epic3Id = epic3.getId();

        assertEquals(epic1Id, epic2Id, "Задачи не совпадают.");
        assertNotEquals(epic1Id, epic3Id, "Задачи совпадают.");
    }


}