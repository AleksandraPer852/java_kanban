import java.util.Collection;
import java.util.HashMap;

public class TaskManager {

    HashMap<Integer, TaskInterface> tasks = new HashMap<>();

    public void addTask(TaskInterface task) {
        task.setId(this.getMaxId() + 1);
        this.tasks.put(task.getId(), task);
    }

    private int getMaxId() {
        int result = 0;

        for (TaskInterface task : this.tasks.values()) {
            if (task.getId() > result) {
                result = task.getId();
            }
        }

        return result;
    }

    public Collection<TaskInterface> getTasks() {
        return this.tasks.values();
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    public TaskInterface getTaskById(int id) {
        return this.tasks.get(id);
    }

    public void updateTask(TaskInterface updatedTask) {
        this.tasks.put(updatedTask.getId(), updatedTask);
    }

    public void deleteTaskById(int id) {
        this.tasks.remove(id);
    }

    public Collection<Subtask> getEpicSubtasks(Epic epic) {
        return epic.getSubtasks();
    }

}
