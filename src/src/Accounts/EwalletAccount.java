package Accounts;

import Accounts.Providers.AccountProvider;

import java.security.Provider;

public class EwalletAccount extends Account{
    public EwalletAccount(AccountProvider provider) {
        super(provider);
    }
}
