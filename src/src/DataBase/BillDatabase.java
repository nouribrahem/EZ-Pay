package DataBase;

import Accounts.Account;
import Bills.BillAPI.BillReceipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillDatabase {
    private Map<Account,List<BillReceipt>> accountBills;
    {
        accountBills = new HashMap<Account,List<BillReceipt>>();
    }
    public void addTransaction(Account account, BillReceipt BillReceipt) {
        if (accountBills.containsKey(BillReceipt)) {
            List<BillReceipt> BillReceipts = accountBills.get(BillReceipt);
            BillReceipts.add(BillReceipt);
        } else {
            List<BillReceipt> BillReceipts = new ArrayList<>();
            BillReceipts.add(BillReceipt);
            accountBills.put(account, BillReceipts);
        }
    }
    public List<BillReceipt> getTransaction(Account account){
        if (accountBills.containsKey(account)) {
            List<BillReceipt> BillReceipts = accountBills.get(account);
            return BillReceipts;
        } else {
            return null;
        }
    }
}
