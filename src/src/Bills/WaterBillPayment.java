package Bills;

import Bills.BillAPI.BillProvider;

import java.util.Scanner;

public class WaterBillPayment extends BillPayment {
//    public void setBillType() {
//
//    }
    public WaterBillPayment() {
        billProvider = new BillProvider();
        billProvider.setProviderName("Water");
    }
    public boolean enterData() {
        Scanner input = new Scanner(System.in);
        WaterData waterData = new WaterData();
        while (true) {
            System.out.print("Please, enter the subscription number : ");
            waterData.setSubscriptionNumber(input.nextLine());
            if (waterData.subscriptionNumber.equals("-1"))
                return false;
            if (billProvider.getStoredReceipts().containsKey(waterData.subscriptionNumber)) {
                data = waterData;
                break;
            } else {
                System.out.println("\nSorry! There is no bill with that subscription number :(\nIf you want to go back enter -1\n");
            }
        }
        return true;
    }

}
