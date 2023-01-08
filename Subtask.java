public class Subtask extends Task {

    protected int epicId;

    public Subtask(int epicId, String title, String description) {
        super(title, description);
        this.epicId = epicId;
    }

    public Subtask(int epicId, String title) {
        super(title);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask #" + this.id + "\n" +
                "Title: " + this.title + "\n" +
                "Description: " + this.description + "\n" +
                "Status: " + this.status;
    }

}
