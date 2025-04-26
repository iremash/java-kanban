package model;

public class Subtask extends Task {
    protected final Epic epic;

    public Subtask(String name, String details, Epic epic) {
        super(name, details);
        this.epic = epic;
        status = TaskStatus.NEW;
    }

    public Subtask(String name, String details, TaskStatus status, Epic epic) {
        super(name, details, status);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }


}
