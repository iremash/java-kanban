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
        Object result = null;
        String location = checkObjectLocation(objectId);

        switch (location) {
            case "tasks":
                result = tasks.get(objectId);
                break;
            case "subtasks":
                result = subtasks.get(objectId);
                break;
            case "epics":
                result = epics.get(objectId);
                break;
            default:
        }
        return result;
    }

    public void removeById(int objectId) {
        String location = checkObjectLocation(objectId);

        switch (location) {
            case "tasks":
                tasks.remove(objectId);
                break;
            case "subtasks":
                Subtask subtask = subtasks.get(objectId);
                Epic epic = epics.get(subtask.getEpicId());
                epic.getSubtasks().remove(subtask);
                subtasks.remove(objectId);
                break;
            case "epics":
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
        epics.get(subtask.getEpicId()).updateSubtask(subtask);
        epics.get(subtask.getEpicId()).checkStatus();
    }

    public void updateEpic(int epicId, Epic epic) {
        epics.put(epicId, epic);
        epic.checkStatus();
    }

    public String checkObjectLocation(int objectId) {
        String location = "";

        for (Integer taskId : tasks.keySet()) {
            if (taskId == objectId) {
                location = "tasks";
                break;
            }
        }
        for (Integer subtaskId : subtasks.keySet()) {
            if (subtaskId == objectId) {
                location = "subtasks";
                break;
            }
        }
        for (Integer epicId : epics.keySet()) {
            if (epicId == objectId) {
                location = "epics";
                break;
            }
        }
        return location;
    }


}
