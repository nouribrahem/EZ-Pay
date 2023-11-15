package Transaction;

import Accounts.Account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private ITransfer transferStrategy;
    private LocalDateTime dateTime;
    private double amount;
    private TransactionType type;
    public Object getTransferStrategy() {
        return transferStrategy;
    }

    public void setTransferStrategy(ITransfer transferStrategy) {
        this.transferStrategy = transferStrategy;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateTime = now;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }


    public void setTransfer(ITransfer strategy){
        transferStrategy = strategy;
    }
    public Boolean doTransfer(Account recievingAccount, Account sendingAccount, double amount){
        return transferStrategy.transfer(sendingAccount,recievingAccount,amount);
    }
    public Transaction makeTransaction(Account recievingAccount, Account sendingAccount, double amount){
        setDateTime();
        setAmount(amount);
        setType(TransactionType.send);
        setTransfer(new Transfer());
        if(doTransfer(recievingAccount, sendingAccount, amount)){
            return this;
        }
        else{
            return null;
        }
    }
}
