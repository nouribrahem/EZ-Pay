package Bills;

import Bills.BillAPI.BillProvider;

import java.util.Scanner;

public class ElectricityBillPayment extends BillPayment {

//    public void setBillType() {
//
//    }
    public ElectricityBillPayment() {
        billProvider = new BillProvider();
        billProvider.setProviderName("Electricity");
    }
    public boolean enterData() {
        Scanner input = new Scanner(System.in);
        ElectricityData electricityData = new ElectricityData();
        while (true) {
            System.out.print("Please, enter the subscription number : ");
            electricityData.setSubscriptionNumber(input.nextLine());
            if (electricityData.subscriptionNumber.equals("-1"))
                return false;
            if (billProvider.getStoredReceipts().containsKey(electricityData.subscriptionNumber)) {
                data = electricityData;
                break;
            } else {
                System.out.println("\nSorry! There is no bill with that subscription number :(\nIf you want to go back enter -1\n");
            }
        }
        return true;
    }

}
