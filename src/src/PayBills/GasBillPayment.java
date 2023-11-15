package PayBills;

import java.util.Scanner;

public class GasBillPayment extends BillPayment {
//    public void setBillType() {
//
//    }
    public GasBillPayment() {
        billProvider.setProviderName("Gas");
    }
    public void enterData() {
        Scanner input = new Scanner(System.in);
        GasData gasData = new GasData();
        System.out.print("Please, enter the subscription number : ");
        gasData.setSubscriptionNumber(input.nextLine());
        System.out.print("Please, enter the reading : ");
        gasData.setReading(input.nextLine());
        data = gasData;

    }

}
