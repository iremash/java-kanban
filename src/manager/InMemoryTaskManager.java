package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private final Managers manager = new Managers();
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HistoryManager history = manager.getDefaultHistory();
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
    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    @Override
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    @Override
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public ArrayList<Subtask> getEpicsSubtasks(Epic epic) {
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
    public Object getById(int objectId) {
        Object result = Location.NOT_FOUND;
        Location location = checkObjectLocation(objectId);

        switch (location) {
            case TASKS:
                result = tasks.get(objectId);
                history.add(tasks.get(objectId));
                break;
            case SUBTASKS:
                result = subtasks.get(objectId);
                history.add(subtasks.get(objectId));
                break;
            case EPICS:
                result = epics.get(objectId);
                history.add(epics.get(objectId));
                break;
            default:
        }
        return result;
    }

    @Override
    public void removeById(int objectId) {
        Location location = checkObjectLocation(objectId);

        switch (location) {
            case TASKS:
                tasks.remove(objectId);
                break;
            case SUBTASKS:
                Subtask subtask = subtasks.get(objectId);
                Epic epic = subtask.getEpic();
                epic.getSubtasks().remove(subtask);
                subtasks.remove(objectId);
                break;
            case EPICS:
                epics.remove(objectId);
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
    public ArrayList<Task> getHistory() {
        return history.getHistory();
    }

}
