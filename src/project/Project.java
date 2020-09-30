package project;


/** Main Container for the project... Psuedo code away */

/* This is a Project Manager for overall project management but also a manager
*  for users and leaders to track their time over multiple projects. The aim of the
*  Calendar is to schedule time and ability for individual team members AND co-operative
*  tasks between multiple team members
*** In the Project package we must communicate with the user package and the Calendar package
*   to provide a comprehensive view for team leaders and members alike. */

// Project Container (this)
    // Dates
        // Time
            // People
            //Tasks

    // Dates -
    // Contains all the dates for the project,
    // Each date will have its own TIME
    // Ability to select dates for months and weeks,
    // Ability to set a working week (mon-fri)
    // Each day will be selectable as a working day or not
    // Day will have available and unavailable team members on that day showing

        // Time
        // Time will be 24 h clock
        // Time will be adjustable per day for working times
        // Time will

            // People
            // People will have their own Date and Time

public class Project {
    int projectID;
    String projectName = "";

    public Project(int projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }
}
