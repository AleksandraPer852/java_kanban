import java.util.Collection;
import java.util.HashMap;

public class Epic extends Task implements TaskInterface {

    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(String title, String description) {
        super(title, description);
    }

    public Epic(String title) {
        super(title);
    }

    public void addSubtask(Subtask newSubtask) {
        newSubtask.setId(this.getMaxId() + 1);
        newSubtask.setStatusNew();

        this.subtasks.put(newSubtask.getId(), newSubtask);
        updateEpicStatus();
    }

    private int getMaxId() {
        int result = 0;

        for (Subtask task : this.subtasks.values()) {
            if (task.getId() > result) {
                result = task.getId();
            }
        }

        return result;
    }

    public void updateSubtasks(Collection<Subtask> subtasks) {
        for (Subtask subtask : subtasks) {
            this.subtasks.put(subtask.getId(), subtask);
        }
        updateEpicStatus();
    }

    public Collection<Subtask> getSubtasks() {
        return this.subtasks.values();
    }

    private void updateEpicStatus() {
        boolean areAllSubtasksNew = true;
        boolean areAllSubtasksDone = true;
        boolean isEpicInProgress = false;

        for (Subtask subtask : this.subtasks.values()) {
            if (!subtask.getStatus().equals(TaskInterface.STATUS_NEW)) {
                areAllSubtasksNew = false;
            }
            if (!subtask.getStatus().equals(TaskInterface.STATUS_DONE)) {
                areAllSubtasksDone = false;
            }
        }
        if (!areAllSubtasksDone && !areAllSubtasksNew) {
            isEpicInProgress = true;
        }

        if (areAllSubtasksNew) {
            this.setStatusNew();
        }
        if (areAllSubtasksDone) {
            this.setStatusDone();
        }
        if (isEpicInProgress) {
            this.setStatusInProgress();
        }
    }

    @Override
    public String toString() {
        String result = "Epic #" + this.id + "\n" +
                "Title: " + this.title + "\n" +
                "Description: " + this.description + "\n" +
                "Status: " + this.status + "\n";

        result += "***********************\n";
        result += "Epic #" + this.id + " subtasks:" + "\n";
        for (Subtask subtask : this.subtasks.values()) {
            result += "-----------------------\n";
            result += subtask.toString() + "\n";
        }
        result += "***********************";

        return result;
    }

}
