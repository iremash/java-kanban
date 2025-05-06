import manager.InMemoryTaskManager;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Task task1 = new Task("Lol", "bebebbebeb", TaskStatus.IN_PROGRESS);
        Task task2 = new Task("kek", "pupupupp");

        Epic epic1 = new Epic("vow", "jkjaslfkl");

        taskManager.addEpic(epic1);

        Subtask subtask1 = new Subtask("io", "njn", TaskStatus.DONE, epic1);
        Subtask subtask2 = new Subtask("i", "njhjn", TaskStatus.DONE, epic1);
        Subtask subtask3 = new Subtask("o", "nnkjn", TaskStatus.NEW, epic1);

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask1);

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        int id = subtask2.getId();
        System.out.println(taskManager.getById(subtask1.getId()));
        taskManager.removeById(id);

        System.out.println(taskManager.getEpicsSubtasks(epic1));

        System.out.println(epic1.getStatus());

        Subtask subtask4 = new Subtask("j", "jnijsufi", epic1);
        taskManager.updateSubtask(subtask1.getId(), subtask4);
        System.out.println(taskManager.getSubtasks());
        System.out.println(taskManager.getEpicsSubtasks(epic1));
        System.out.println(taskManager.getById(id));
        printAllTasks(taskManager);

    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks().values()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics().values()) {
            System.out.println(epic);

            for (Task task : manager.getEpicsSubtasks((Epic) epic)) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks().values()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }

}