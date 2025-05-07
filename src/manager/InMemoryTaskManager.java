package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.*;

import static manager.Managers.*;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final HistoryManager history = getDefaultHistory();
    private int id = 0;

    public int generateId() {
        id++;
        return id;
    }

    @Override
    public void addTask(Task task) {
        task.setId(generateId());
        for (Task oldTask : tasks.values()) {
            if (oldTask.equals(task)) {
                task.setId(oldTask.getId());
                break;
            }
        }
        tasks.put(task.getId(), task);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(generateId());
        for (Task oldEpic : epics.values()) {
            if (epic.equals(oldEpic)) {
                epic.setId(oldEpic.getId());
                break;
            }
        }
        epics.put(epic.getId(), epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        Epic epic = subtask.getEpic();

        if (!epic.hasSubtask(subtask)) {
            subtask.setId(generateId());
            subtasks.put(subtask.getId(), subtask);
            epic.getSubtasks().add(subtask);
            epic.checkStatus();
        }
    }

    @Override
    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    @Override
    public Map<Integer, Epic> getEpics() {
        return epics;
    }

    @Override
    public Map<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public List<Subtask> getEpicsSubtasks(Epic epic) {
        return epic.getSubtasks();
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
    }

    @Override
    public Task getById(int taskId) {
        Location location = checkObjectLocation(taskId);
        Task task = null;

        switch (location) {
            case TASKS:
                history.add(tasks.get(taskId));
                task = tasks.get(taskId);
                break;
            case SUBTASKS:
                history.add(subtasks.get(taskId));
                task = subtasks.get(taskId);
                break;
            case EPICS:
                history.add(epics.get(taskId));
                task = epics.get(taskId);
                break;
            default:
        }
        return task;
    }

    @Override
    public void removeById(int taskId) {
        Location location = checkObjectLocation(taskId);

        switch (location) {
            case TASKS:
                tasks.remove(taskId);
                break;
            case SUBTASKS:
                Subtask subtask = subtasks.get(taskId);
                Epic epic = subtask.getEpic();
                epic.getSubtasks().remove(subtask);
                subtasks.remove(taskId);
                break;
            case EPICS:
                epics.remove(taskId);
                break;
            default:
        }
    }

    @Override
    public void updateTask(int taskId, Task task) {
        tasks.put(taskId, task);
    }

    @Override
    public void updateSubtask(int subtaskId, Subtask subtask) {
        subtasks.put(subtaskId, subtask);
        subtask.getEpic().updateSubtask(subtask);
        subtask.getEpic().checkStatus();
    }

    @Override
    public void updateEpic(int epicId, Epic epic) {
        epics.put(epicId, epic);
        epic.checkStatus();
    }

    public Location checkObjectLocation(int objectId) {
        if (tasks.containsKey(objectId)) {
            return Location.TASKS;
        }
        if (subtasks.containsKey(objectId)) {
            return Location.SUBTASKS;
        }
        if (epics.containsKey(objectId)) {
            return Location.EPICS;
        }
        return Location.NOT_FOUND;
    }

    public enum Location {
        TASKS,
        EPICS,
        SUBTASKS,
        NOT_FOUND
    }

    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }
}
