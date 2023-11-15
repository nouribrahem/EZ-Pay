package DataBase;

import Accounts.Account;
import Bills.BillRecipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillDatabase {
    private Map<Account,List<BillRecipt>> accountBills;
    {
        accountBills = new HashMap<Account,List<BillRecipt>>();
    }
    public void addTransaction(Account account, BillRecipt billRecipt) {
        if (accountBills.containsKey(billRecipt)) {
            List<BillRecipt> billRecipts = accountBills.get(billRecipt);
            billRecipts.add(billRecipt);
        } else {
            List<BillRecipt> billRecipts = new ArrayList<>();
            billRecipts.add(billRecipt);
            accountBills.put(account, billRecipts);
        }
    }
    public List<BillRecipt> getTransaction(Account account){
        if (accountBills.containsKey(account)) {
            List<BillRecipt> billRecipts = accountBills.get(account);
            return billRecipts;
        } else {
            return null;
        }
    }
}
