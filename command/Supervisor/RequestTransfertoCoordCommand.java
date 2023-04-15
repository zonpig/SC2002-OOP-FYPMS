/**
 * The RequestTransfertoCoordCommand class is a command class that handles the transfer of an FYP project from one
 * supervisor to another. It implements the Command interface.
 * The class contains a supervisorAccount instance variable to store the current supervisor account, and a requests
 * ArrayList to store the list of requests made in the system.
 * When the execute method is called, the user is prompted to input the ID of the FYP project to be transferred. If
    the project is already assigned to a supervisor, the user is prompted to input the ID of the new supervisor. If
    the new supervisor ID is valid and is not the same as the ID of the current supervisor, a new RequestTransferSupervisor
    object is created with a new unique ID (which is obtained by adding the size of the requests list to 3000), the ID
    of the current supervisor, the status set to PENDING, the ID of the FYP project to be transferred, and the ID of the new
    supervisor. The new request is then added to the requests list. If the new supervisor ID is not valid or is the same as
    the ID of the current supervisor, an error message is displayed.
*/
package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import command.Command;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import FYPMS.request.*;
import account.AccountManager;
import account.supervisor.SupervisorAccount;

public class RequestTransfertoCoordCommand implements Command {
    /*
     * The RequestTransfertoCoordCommand class represents a command to transfer
     * supervision of an FYP project from the current
     * supervisor to another supervisor. This class implements the Command
     * interface.
     */

    /** The SupervisorAccount object representing the current supervisor */
    private final SupervisorAccount supervisorAccount;

    /**
     * Constructs a new RequestTransfertoCoordCommand object with the given
     * SupervisorAccount object.
     * 
     * @param supervisorAccount The SupervisorAccount object representing the
     *                          current supervisor
     */
    public RequestTransfertoCoordCommand(SupervisorAccount supervisorAccount) {
        this.supervisorAccount = supervisorAccount;
    }

    /** The list of requests in the system */
    final ArrayList<ArrayList<Object>> requests = RequestHistory.getRequestList();

    /**
     * Executes the command to transfer supervision of an FYP project from the
     * current supervisor to another supervisor.
     * Prompts the user to input the ID of the FYP project to be transferred. If the
     * project is already assigned to a supervisor, prompts the user to input the ID
     * of the new supervisor.
     * If the new supervisor ID is valid and not the same as the current supervisor
     * ID, creates a new RequestTransferSupervisor object with a unique ID, the ID
     * of the current supervisor, the status set to PENDING, the ID of the FYP
     * project to be transferred, and the ID of the new supervisor.
     * Adds the new request to the requests list.
     * If the new supervisor ID is invalid or the same as the current supervisor ID,
     * displays an error message.
     */
    public void execute() {

        System.out.println("Input project ID to transfer: ");
        Scanner sc = new Scanner(System.in);
        int fypId = sc.nextInt();
        if (!supervisorAccount.getName().equals(FYPList.getFYPById(fypId).getSupervisorName())) {
            System.out.println();
            System.out.println("Invalid project ID entered");
            return;
        }
        if (FYPList.getFYPById(fypId).getStatus().equals(FYPStatus.ALLOCATED)) {
            System.out.println("Input new supervisor ID: ");
            String newSupervisorID = sc.next();

            ArrayList<SupervisorAccount> supervisors = AccountManager.getSupervisorList();
            for (SupervisorAccount indi : supervisors) {
                if (indi.getLoginId().equals(newSupervisorID)
                        && !(newSupervisorID.equals(supervisorAccount.getLoginId()))) {
                    RequestTransferSupervisor request = new RequestTransferSupervisor(requests.get(3).size() + 3000,
                            supervisorAccount.getLoginId(), RequestStatus.PENDING, fypId, newSupervisorID);
                    requests.get(3).add(request);
                    return;
                }
            }
            System.out.println();
            System.out.println("Invalid SupervisorID entered");

        } else {
            System.out.println("Error: FYP is not allocated to any student");
        }
    }
}
