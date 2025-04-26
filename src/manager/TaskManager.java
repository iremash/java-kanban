package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private int id = 0;

    public int generateId() {
        id++;
        return id;
    }

    public void addTask(Task task) {
        for (Task oldTask : tasks.values()) {
            if (oldTask.equals(task)) {
                return;
            }
        }
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    public void addEpic(Epic epic) {
        for (Task oldEpic : epics.values()) {
            if (epic.equals(oldEpic)) {
                return;
            }
        }
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
    }

    public void addSubtaskToEpic(Epic epic, Subtask subtask) {
        if (!epic.hasSubtask(subtask)) {
            subtask.setId(generateId());
            subtasks.put(subtask.getId(), subtask);
            epic.getSubtasks().add(subtask);
            epic.checkStatus();
        }
    }

    public HashMap<Integer, Task> getAllTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getAllEpics() {
        return epics;
    }

    public HashMap<Integer, Subtask> getAllSubtasks() {
        return subtasks;
    }

    public ArrayList<Subtask> getEpicsSubtasks(Epic epic) {
        return epic.getSubtasks();
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public Object findById(int objectId) {
        Object result = Location.NOT_FOUND;
        Location location = checkObjectLocation(objectId);

        switch (location) {
            case TASKS:
                result = tasks.get(objectId);
                break;
            case SUBTASKS:
                result = subtasks.get(objectId);
                break;
            case EPICS:
                result = epics.get(objectId);
                break;
            default:
        }
        return result;
    }

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

    public void updateTask(int taskId, Task task) {
        tasks.put(taskId, task);
    }

    public void updateSubtask(int subtaskId, Subtask subtask) {
        subtasks.put(subtaskId, subtask);
        subtask.getEpic().updateSubtask(subtask);
        subtask.getEpic().checkStatus();
    }

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


}
