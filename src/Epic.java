import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String details) {
        super(name, details);
        status = TaskStatus.NEW;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void checkStatus() {
        if (subtasks.isEmpty()) {
            status = TaskStatus.NEW;
        } else {
            boolean allTasksDone = true;
            boolean allTasksNew = true;

            for (Subtask subtask : subtasks) {
                if (subtask.getStatus() != TaskStatus.NEW) {
                    allTasksNew = false;
                } else if (subtask.getStatus() != TaskStatus.DONE) {
                    allTasksDone = false;
                }
            }
            if (allTasksDone) {
                status = TaskStatus.DONE;
            } else if (allTasksNew) {
                status = TaskStatus.NEW;
            } else {
                status = TaskStatus.IN_PROGRESS;
            }
        }
    }

    public void updateSubtask(Subtask newSubtask) {
        for (Subtask subtask : subtasks) {
            if (subtask.getId() == newSubtask.getId()) {
                subtasks.remove(subtask);
                subtasks.add(newSubtask);
            }
        }
    }

    public boolean hasSubtask(Subtask anotherSubtask) {
        for (Subtask subtask : subtasks) {
            if (subtask.equals(anotherSubtask)) {
                return true;
            }
        }
        return false;
    }
}
