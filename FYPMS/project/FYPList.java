package FYPMS.project;

import java.util.ArrayList;

/**
 * Represents the list of Final Year Projects (FYPs) the department has approved
 * and are entitled to supervise
 */
public class FYPList {
    private static final ArrayList<FYP> fyps = new ArrayList<>();

    /**
     * Adds a FYP to the list of FYPs the department can supervise
     *
     * @param fyp FYP to be added
     */

    public static ArrayList<FYP> getFypList() {
        return fyps;
    }
    public void addFYP(FYP fyp) {
        fyps.add(fyp);
    }

    public static ArrayList<FYP> getSuperFypList(String supervisorName) {
        ArrayList<FYP> superFyps = new ArrayList<FYP>();
        for (FYP fyp : fyps) {
            if (fyp.getSupervisorName().equals(supervisorName)) {
                superFyps.add(fyp);
            }
        }
        return superFyps;
    }

    /**
     * Prints the available FYPs for viewing by students
     */
    public static void listAvailableFYPsForStudents() {
        int fypCount = 1;
        System.out.println();
        System.out.println("List of Available Final Year Projects");
        System.out.println();
        for (FYP fyp : fyps) {
            if (fyp.getStatus() == FYPStatus.AVAILABLE) {
                System.out.println("============= FYP ID " + fyp.getProjectId() + " ==============");
                fyp.printFYPDetails();
                System.out.println();
                fypCount++;
            }
        }
        System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects available! =====");
    }

    /**
     * Prints all FYPs in the system for faculty members
     */

    /**
     * Returns a FYP using the projectId inputted
     *
     */
    public static FYP getFYPById(int FYPId) {
        for (FYP fyp : fyps) {
            if (fyp.getProjectId() == FYPId) {
                return fyp;
            }
        }
        return null;
    }

    // /**
    //  * Searches for a FYP using a keyword inputted by users
    //  *
    //  * @param keyword Keyword used to search for a FYP title
    // //  */
    // public void searchFYPSByKeyword(String keyword) {
    //     int numOfResults = 0;

    //     System.out.println();
    //     System.out.println("Search Results for Final Year Projects titled \"" + keyword + "\"");
    //     System.out.println();

    //     for (FYP fyp : fyps) {
    //         if (fyp.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
    //             System.out.println("============= FYP ID " + fyp.getProjectId() + " ==============");
    //             fyp.printFYPDetails();
    //             System.out.println();
    //             System.out.println("Search Results for Final Year Projects titled \"" + keyword + "\"");
    //             System.out.println();
    //             numOfResults++;
    //         }
    //     }
    //     if (numOfResults == 0) {
    //         System.out.println("No such Final Year Project found in the system!");
    //     } else {
    //         System.out.println("===== " + numOfResults + " Final Year Projects found! =====");
    //     }
    // }

    // public ArrayList<FYP> searchFYPSByKeywordWithReturn(String keyword) {
    //     ArrayList<FYP> fypSearchResults = new ArrayList<>();

    //     for (FYP fyp : fyps) {
    //         if (fyp.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
    //             fypSearchResults.add(fyp);
    //         }
    //     }
    //     return fypSearchResults;
    // }
}
