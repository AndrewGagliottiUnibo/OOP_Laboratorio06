package it.unibo.oop.lab.exception2;

import org.junit.Test;

public final class TestStrictBankAccount {

    private static final int INITIAL_VALUE = 10_000;
    private static final int NUM_TRANSACTION_AVAILABLE = 30;
    private static final double N_OP_TO_DO = 10;
    private static final String MESSAGE_STRING = ": Wrong user identifier!";

    @Test
    public void testBankOperations() {

        final AccountHolder user1 = new AccountHolder("Andrew", "Gagliotti", 23);
        final AccountHolder user2 = new AccountHolder("Desmon", "DeLaverge", 46);
        final BankAccount account1 = new StrictBankAccount(user1.getUserID(), INITIAL_VALUE, NUM_TRANSACTION_AVAILABLE);
        final BankAccount account2 = new StrictBankAccount(user2.getUserID(), INITIAL_VALUE, NUM_TRANSACTION_AVAILABLE);

        /* 2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in
         * presenza di un id utente errato, oppure al superamento del numero di
         * operazioni ATM gratuite.
         */
        try {
            account1.deposit(user2.getUserID(), INITIAL_VALUE);
            System.out.println(user2.getName() + ": deposit done. Total operations done: " + account2.getTransactionCount());
        } catch (WrongAccountHolderException exception) {
            System.out.println(user2.getName() + MESSAGE_STRING);
        }

        try {
            account1.deposit(user1.getUserID(), INITIAL_VALUE);
            System.out.println(user1.getName() + ": deposit done. Total operations done: " + account1.getTransactionCount());
        } catch (WrongAccountHolderException exception) {
            System.out.println(user1.getName() + MESSAGE_STRING);
        }

        /*
         * L'ordine di analisi delle possibili eccezioni procede in base ai dati che vengono pres in esame per primi, in questo
         * caso viene prima analizzato l'ID poi il numero di transazioni totali. se un programmatore disattento inserisse un valore 
         * a caso al posto del metodo getter potrebbe sicuramente lanciare una eccezione
         */
        try {
            for (int i = 0; i <= NUM_TRANSACTION_AVAILABLE; i++) {
                account2.depositFromATM(user2.getUserID(), N_OP_TO_DO);
            }
            System.out.println(user2.getName() + ": cycle deposit done. Total operations done: " + account2.getTransactionCount());
        } catch (TransactionsOverQuotaException exception) {
            System.out.println(user2.getName() + ": End of available Transactions");
        } catch (WrongAccountHolderException exception) {
            System.out.println(user2.getName() + MESSAGE_STRING);
        }

        try {
            for (int i = 0; i < N_OP_TO_DO; i++) {
                account1.depositFromATM(user1.getUserID(), N_OP_TO_DO);
            }
            System.out.println(user1.getName() + ": cycle deposit done. Total operations done: " + account1.getTransactionCount());
        } catch (TransactionsOverQuotaException exception) {
            System.out.println(user1.getName() + ": End of available Transactions");
        } catch (WrongAccountHolderException exception) {
            System.out.println(user1.getName() + MESSAGE_STRING);
        }
    }
}
