package Bills;

import java.util.Scanner;

public class WaterBillPayment extends BillPayment {
//    public void setBillType() {
//
//    }
    public WaterBillPayment() {
        billProvider.setProviderName("Water");
    }
    public void enterData() {
        Scanner input = new Scanner(System.in);
        WaterData waterData = new WaterData();
        System.out.print("Please, enter the subscription number : ");
        waterData.setSubscriptionNumber(input.nextLine());

        data = waterData;
    }

}
