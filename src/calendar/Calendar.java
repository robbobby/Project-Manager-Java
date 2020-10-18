package calendar;



public class Calendar {

    String projectID = "";
    int projectUniqueID = 0;

    // ##### Constructor ##### //
    public Calendar(String projectID, int projectUniqueID) {
        this.projectID = projectID;
        this.projectUniqueID = projectUniqueID;
    }

    private Task[] setInitData() {
        Task[] tasks = null;
        if (projectUniqueID != 0)
        {
            // TODO Get details from mysql
        }
        return tasks;
    }

    public void date() {

    }
}