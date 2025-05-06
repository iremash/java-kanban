package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubtask(Subtask subtask);

    HashMap<Integer, Task> getTasks();

    HashMap<Integer, Epic> getEpics();

    HashMap<Integer, Subtask> getSubtasks();

    ArrayList<Subtask> getEpicsSubtasks(Epic epic);

    void deleteAllTasks();

    void deleteAllSubtasks();

    void deleteAllEpics();

    Object getById(int objectId);

    void removeById(int objectId);

    void updateTask(int taskId, Task task);

    void updateSubtask(int subtaskId, Subtask subtask);

    void updateEpic(int epicId, Epic epic);

    ArrayList<Task> getHistory();
}