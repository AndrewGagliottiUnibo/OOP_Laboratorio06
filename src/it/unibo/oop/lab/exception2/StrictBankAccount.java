package it.unibo.oop.lab.exception2;

public class StrictBankAccount implements BankAccount {

    private final int usrID;
    private double balance;
    private int totalTransactionCount;
    private final int maximumAllowedATMTransactions;
    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;
    private static final int RESET_OPERATIONS = 0;

    //costruttore
    public StrictBankAccount(final int usrID, final double balance, final int maximumAllowedAtmTransactions) {
        this.usrID = usrID;
        this.balance = balance;
        this.maximumAllowedATMTransactions = maximumAllowedAtmTransactions;
    }

    /**
     * @param usrID
     * @param amount
     */
    @Override
    public void deposit(final int usrID, final double amount) throws WrongAccountHolderException {
        if (checkUser(usrID)) {
            this.balance += amount;
            increaseTransactionsCount();
        } else {
            throw new WrongAccountHolderException();
        }
    }

    /**
     * @param usrID
     * @param amount
     */
    @Override
    public void withdraw(final int usrID, final double amount) throws WrongAccountHolderException, NotEnoughFoundsException {
        if (checkUser(usrID)) {
            if (isWithdrawAllowed(amount)) {
                this.balance -= amount;
                increaseTransactionsCount();
            } else {
                throw new NotEnoughFoundsException();
            }
        } else {
            throw new WrongAccountHolderException();
        }
    }

    /**
     * @param usrID
     * @param amount
     */
    @Override
    public void depositFromATM(final int usrID, final double amount) throws  TransactionsOverQuotaException, WrongAccountHolderException {
        if (totalTransactionCount < maximumAllowedATMTransactions) {
            this.deposit(usrID, amount - StrictBankAccount.ATM_TRANSACTION_FEE);
            increaseTransactionsCount();
        } else {
            throw new TransactionsOverQuotaException();
        }
    }

    /**
     * @param usrID
     * @param amount
     */
    @Override
    public void withdrawFromATM(final int usrID, final double amount) throws TransactionsOverQuotaException, WrongAccountHolderException, NotEnoughFoundsException {
        if (totalTransactionCount < maximumAllowedATMTransactions) {
            this.withdraw(usrID, amount + StrictBankAccount.ATM_TRANSACTION_FEE);
        } else {
            throw new TransactionsOverQuotaException();
        }
    }

    /**
     * @return balance
     */
    @Override
    public double getBalance() {
        return this.balance;
    }

    /**
     * @return transaction
     */
    @Override
    public int getTransactionCount() {
        return this.totalTransactionCount;
    }

    /**
     * @param usrID
     */
    public void computeManagementFees(final int usrID) {
        final double feeAmount = MANAGEMENT_FEE + (totalTransactionCount * StrictBankAccount.TRANSACTION_FEE);
        if (checkUser(usrID) && isWithdrawAllowed(feeAmount)) {
            balance -= MANAGEMENT_FEE + totalTransactionCount * StrictBankAccount.TRANSACTION_FEE;
            totalTransactionCount = RESET_OPERATIONS;
        }
    }

    private boolean checkUser(final int id) {
        return this.usrID == id;
    }

    private boolean isWithdrawAllowed(final double amount) {
        return balance > amount;
    }

    private void increaseTransactionsCount() {
        this.totalTransactionCount++;
    }
}
