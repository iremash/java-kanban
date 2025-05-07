package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    List<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String details) {
        super(name, details);
        status = TaskStatus.NEW;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void checkStatus() {
        if (subtasks.isEmpty()) {
            status = TaskStatus.NEW;
        } else {
            boolean allTasksSame = true;

            for (int i = 0; i < subtasks.size() - 1; i++) {
                if (subtasks.get(i).getStatus() != subtasks.get(i + 1).getStatus()) {
                    allTasksSame = false;
                    break;
                }
            }
            if (allTasksSame) {
                status = subtasks.getFirst().getStatus();
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
                anotherSubtask.setId(subtask.getId());
                return true;
            }
        }
        return false;
    }
}
