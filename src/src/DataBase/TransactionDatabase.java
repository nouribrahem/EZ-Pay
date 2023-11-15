package DataBase;

import Accounts.Account;
import Transaction.Transaction;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDatabase {
    private Map<Account, List<Transaction>> accountTransactions;
    {
        accountTransactions = new HashMap();
    }
    public void addTransaction(Account sendingAccount, Transaction userTransaction) {
        if (accountTransactions.containsKey(sendingAccount)) {
            List<Transaction> transactions = accountTransactions.get(sendingAccount);
            transactions.add(userTransaction);
        } else {
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(userTransaction);
            accountTransactions.put(sendingAccount, transactions);
        }
    }
    public List<Transaction> getTransaction(Account account){
        if (accountTransactions.containsKey(account)) {
            List<Transaction> transactions = accountTransactions.get(account);
            return transactions;
        } else {
            return null;
        }
    }
}
