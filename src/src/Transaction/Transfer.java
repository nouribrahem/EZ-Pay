package Transaction;

import Accounts.Account;

public class Transfer implements ITransfer {
    @Override
    public Boolean transfer(Account recievingAccount, Account sendingAccount, double amount) {
        Boolean recieved = recievingAccount.getProvider().updateAccountBalance(recievingAccount,amount);
        System.out.println("Receiving " +recievingAccount.getProvider().getAccountBalance(recievingAccount));
        Boolean sent = sendingAccount.getProvider().updateAccountBalance(sendingAccount,0-amount);
        return recieved && sent;
    }
}
