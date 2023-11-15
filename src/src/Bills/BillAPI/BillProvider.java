package Bills.BillAPI;

import Bills.UtilityData;

import java.util.HashMap;

public class BillProvider {
    private String providerName;

//    private BillReceipt receipt;

    private HashMap<String, BillReceipt> storedReceipts;
    public BillProvider() {
        BillReceipt r1 = new BillReceipt();
        r1.setAmount(145);
        r1.setTax(0.2);
        r1.setMonth(10);
        r1.setYear(2022);
//        r1.setType("Electricity");
        storedReceipts.put("123456", r1);
        BillReceipt r2 = new BillReceipt();
        r2.setAmount(73);
        r2.setTax(0.15);
        r2.setMonth(11);
        r2.setYear(2023);
//        r2.setType("Water");
        storedReceipts.put("782062", r2);
        BillReceipt r3 = new BillReceipt();
        r3.setAmount(223);
        r3.setTax(0.13);
        r3.setMonth(11);
        r3.setYear(2022);
//        r3.setType("Electricity");
        storedReceipts.put("300163", r3);
        BillReceipt r4 = new BillReceipt();
        r4.setAmount(42.8);
        r4.setTax(0.2);
        r4.setMonth(9);
        r4.setYear(2023);
//        r4.setType("Gas");
        storedReceipts.put("512934", r4);
        BillReceipt r5 = new BillReceipt();
        r5.setAmount(90);
        r5.setTax(0.12);
        r5.setMonth(11);
        r5.setYear(2023);
//        r5.setType("Gas");
        storedReceipts.put("001122", r5);
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderName() {
        return providerName;
    }

//    public BillReceipt getReceipt() {
//        return receipt;
//    }

//    public void setReceipt(BillReceipt receipt) {
//        this.receipt = receipt;
//    }

    public BillReceipt getBillReceipt(UtilityData data) {
        return storedReceipts.get(data.getSubscriptionNumber());
    }
}
