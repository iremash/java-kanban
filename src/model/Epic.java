package model;

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
