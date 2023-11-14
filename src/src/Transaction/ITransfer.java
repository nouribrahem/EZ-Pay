package Transaction;

import Accounts.Account;

public interface ITransfer {
    public Boolean transfer(Account recievingAccount, Account sendingAccount, double amount);
}
