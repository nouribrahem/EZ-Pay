package Bills;

import java.util.Scanner;
import Bills.BillAPI.*;

public class GasBillPayment extends BillPayment {
//    public void setBillType() {
//
//    }
    public GasBillPayment(){
        billProvider = new BillProvider();
        billProvider.setProviderName("Gas");
    }
    public boolean enterData() {
        Scanner input = new Scanner(System.in);
        GasData gasData = new GasData();
        while (true) {
            System.out.print("Please, enter the subscription number : ");
            gasData.setSubscriptionNumber(input.nextLine());
            if (gasData.subscriptionNumber.equals("-1"))
                return false;
            if (billProvider.getStoredReceipts().containsKey(gasData.subscriptionNumber)) {
                data = gasData;
                break;
            } else {
                System.out.println("\nSorry! There is no bill with that subscription number :(\nIf you want to go back enter -1\n");
            }
        }
        System.out.print("Please, enter the reading : ");
        gasData.setReading(input.nextLine());
        return true;
    }

}
