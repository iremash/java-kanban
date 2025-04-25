public class Subtask extends Task {
    protected final int epicId;

    public Subtask(String name, String details, int epicId) {
        super(name, details);
        this.epicId = epicId;
        status = TaskStatus.NEW;
    }

    public Subtask(String name, String details, TaskStatus status, int epicId) {
        super(name, details, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }


}
