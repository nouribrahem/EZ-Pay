package Bills;

import Accounts.Account;
import Accounts.InstaPayAccount;
import Bills.BillAPI.BillProvider;
import UserManagement.User;

import java.time.Instant;
import java.util.Scanner;

abstract public class BillPayment {
    protected BillProvider billProvider;
    protected UtilityData data;

//    public BillPayment() {
//        billProvider = new BillProvider();
//    }
    public void setBillProvider(BillProvider billProvider) {
        this.billProvider = billProvider;
    }

    public BillProvider getBillProvider() {
        return billProvider;
    }

    public UtilityData getData() {
        return data;
    }

    public void setData(UtilityData data) {
        this.data = data;
    }

    public void billOptions(User user) {
        Scanner input = new Scanner(System.in);
        if (!enterData())
            return;
        boolean validInternalChoice = false;
        while (!validInternalChoice) {
            System.out.println("\nDo you want to :\n" +
                    " 1. Pay the bill .\n" +
                    " 2. Show the receipt .\n" +
                    " 3. Back to the previous menu .\n");
            int billInternalChoice = input.nextInt();
            switch (billInternalChoice) {
                case 1: {
                    payBill(user.getInstaPayAccount().getAccount());
                    validInternalChoice = true;
                    break;
                }
                case 2: {
                    showReceipt(user);
                    break;
                }
                case 3: {
                    validInternalChoice = true;
                    break;
                }
                default: {
                    System.out.println("Sorry! Invalid choice :(\n");
                }
            }

        }
    }
    public void payBill(Account account) {
        double amount = billProvider.getBillReceipt(data).calcTotalAmount();
        System.out.println("Your balance : " + account.getBalance());
        System.out.println("Your bill receipt total amount : " + amount);
        if (confirmPayment(account)) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nAre you sure you want to pay the bill ?\n 1. Yes.\n 2. No.");
            int choice = input.nextInt();
            if (choice == 1) {
                account.setBalance(account.getBalance() - amount);
                System.out.println("\nYou have paid the bill successfully.\n");
                System.out.println("Your current balance : " + account.getBalance());
            }
        } else {
            System.out.println("\nSorry! You don't have enough money in your balance to pay the bill :(\n");
        }
    }

//    public void setUtilityDataType() {
//        if (this instanceof GasBillPayment)
//            data = new GasData();
//        else if (this instanceof ElectricityBillPayment)
//            data = new ElectricityData();
//        else if (this instanceof WaterBillPayment)
//            data = new WaterData();
//    }

    abstract public boolean enterData();

    public void showReceipt(User user) {
        System.out.println("\n\n-----------------------------------------------");
        System.out.println("           " + billProvider.getProviderName() + " Bill");
        System.out.println(" Consumer name : " + user.getUserName());
        System.out.println(" Account number : " + user.getInstaPayAccount().getAccount().getMobileNumber());
        System.out.println(" Subscription number : " + data.getSubscriptionNumber());
        System.out.println(" Month : " + billProvider.getBillReceipt(data).getMonth() + "/" + billProvider.getBillReceipt(data).getYear());
        System.out.println(" Amount : " + billProvider.getBillReceipt(data).getAmount());
        System.out.println(" Tax : " + (billProvider.getBillReceipt(data).getTax() * 100) + "%");
        System.out.println(" Total Amount : " + billProvider.getBillReceipt(data).calcTotalAmount());
        System.out.println("-----------------------------------------------\n");

    }

    public boolean confirmPayment(Account account) {
        if (account.getBalance() >= billProvider.getBillReceipt(data).calcTotalAmount())
                return true;
        return false;
    }

}
