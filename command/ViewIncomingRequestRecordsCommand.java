package command;

import FYPMS.request.Request;
import FYPMS.request.RequestHistory;
import account.Account;
import java.util.ArrayList;

public class ViewIncomingRequestRecordsCommand implements Command {
    private final Account account;

    public ViewIncomingRequestRecordsCommand(Account account) {
        this.account = account;
    }

    public void execute() {
        int RequestCount = 0;
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        ArrayList<ArrayList<Object>> requests = RequestHistory.getRequestList();
        for (ArrayList<Object> request : requests) {
            for (Object indivRequest : request) {
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesteeID().equals(account.getLoginId())) {
                    System.out.println(
                            "============= Request ID " + indivCastedRequest.getRequestID() + " ==============");
                    indivCastedRequest.printDetails();
                    System.out.println();
                    RequestCount++;
                }

            }

        }

        if (RequestCount == 0) {
            System.out.println("You have no incoming requests.");
        } else {
            System.out.print("You have " + RequestCount + " incoming requests.");
        }
    }
}
