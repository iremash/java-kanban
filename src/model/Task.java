package model;

import java.util.Objects;

public class Task {
    protected String name;
    protected String details;
    protected TaskStatus status;
    protected int id;

    public Task(String name, String details) {
        this.name = name;
        this.details = details;
        status = TaskStatus.NEW;
    }

    public Task(String name, String details, TaskStatus status) {
        this.name = name;
        this.details = details;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Task anotherTask = (Task) obj;
        return Objects.equals(name, anotherTask.getName()) && Objects.equals(details, anotherTask.getDetails());
    }

    @Override
    public int hashCode() {
        int result = 17 + Objects.hashCode(name);
        result *= 31;
        result += Objects.hashCode(details);
        return result;
    }
}
