package it.unibo.oop.lab.exception2;

/**
 * Models a generic bank account.
 * 
 */
public interface BankAccount {

    void withdraw(int usrID, double amount) throws WrongAccountHolderException, NotEnoughFoundsException;

    void deposit(int usrID, double amount) throws WrongAccountHolderException;

    void depositFromATM(int usrID, double amount) throws TransactionsOverQuotaException, WrongAccountHolderException;

    void withdrawFromATM(int usrID, double amount) throws TransactionsOverQuotaException, WrongAccountHolderException, NotEnoughFoundsException;

    double getBalance();

    int getTransactionCount();
}
