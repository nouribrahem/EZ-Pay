package Bills;

import java.util.Scanner;

public class ElectricityBillPayment extends BillPayment {

//    public void setBillType() {
//
//    }
    public ElectricityBillPayment() {
        billProvider.setProviderName("Electricity");
    }
    public void enterData() {
        Scanner input = new Scanner(System.in);
        ElectricityData electricityData = new ElectricityData();
        System.out.print("Please, enter the subscription number : ");
        electricityData.setSubscriptionNumber(input.nextLine());
    }

}
