public interface TaskInterface {

    final String STATUS_NEW = "NEW";
    final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    final String STATUS_DONE = "DONE";

    public void setStatusNew();
    public void setStatusInProgress();
    public void setStatusDone();
    public String getStatus();
    public void setId(int id);
    public int getId();
    public String getTitle();
    public void setTitle(String title);
    public String getDescription();
    public void setDescription(String description);

}
