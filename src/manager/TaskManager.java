package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.*;

public interface TaskManager {
    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubtask(Subtask subtask);

    Map<Integer, Task> getTasks();

    Map<Integer, Epic> getEpics();

    Map<Integer, Subtask> getSubtasks();

    List<Subtask> getEpicsSubtasks(Epic epic);

    void deleteAllTasks();

    void deleteAllSubtasks();

    void deleteAllEpics();

    Task getById(int objectId);

    void removeById(int objectId);

    void updateTask(int taskId, Task task);

    void updateSubtask(int subtaskId, Subtask subtask);

    void updateEpic(int epicId, Epic epic);

    List<Task> getHistory();

}