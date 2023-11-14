package Transaction;

import Accounts.Account;

public class Transfer implements ITransfer {
    @Override
    public Boolean transfer(Account recievingAccount, Account sendingAccount, double amount) {
        Boolean recieved = recievingAccount.getProvider().updateAccountBalance(amount);
        Boolean sent = sendingAccount.getProvider().updateAccountBalance(amount);
        return recieved && sent;
    }
}
