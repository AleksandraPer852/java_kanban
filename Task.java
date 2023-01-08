public class Task implements TaskInterface {

    protected int id;
    protected String title = "";
    protected String description = "";
    protected String status;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task(String title) {
        this.title = title;
    }

    public void setStatusNew() {
        this.status = TaskInterface.STATUS_NEW;
    }

    public void setStatusInProgress() {
        this.status = TaskInterface.STATUS_IN_PROGRESS;
    }

    public void setStatusDone() {
        this.status = TaskInterface.STATUS_DONE;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task #" + this.id + "\n" +
                "Title: " + this.title + "\n" +
                "Description: " + this.description + "\n" +
                "Status: " + this.status;
    }

}
