package FYPMS.request;

import account.AccountManager;

public class RequestTransferSupervisor extends Request {

    private final String newSupervisorID;

    public RequestTransferSupervisor(int requestID, String requesterID, RequestStatus requestStatus, int fypID,
            String newSupervisorID) {
        super(requestID, requesterID, AccountManager.getCoordinatorList().get(0).getLoginId(), requestStatus,
                RequestRelationship.SUPERVISORCoordinator, RequestType.TRANSFER_SUPERVISOR, fypID);
        this.newSupervisorID = newSupervisorID;
    }

    public String getNewSupervisorID() {
        return newSupervisorID;
    }

    public void printDetails() {
        System.out.println("Requester: " + getRequesterID());
        System.out.println("Requestee: " + getRequesteeID());
        System.out.println("Request Type: " + getRequestType());
        System.out.println("Request Status: " + getRequestStatus());
        System.out.println("Request Relationship: " + getRequestRelationship());
        System.out.println("FYP ID: " + getFypID());
        System.out.println("New Supervisor ID: " + getNewSupervisorID());
        System.out.println();
    }

}
