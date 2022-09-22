package exercise.three.banking.bank;

import exercise.three.banking.holder.AccountHolder;
import exercise.three.banking.holder.Person;

/**
 * The public methods for the {@link Bank} class.
 */
public interface BankInterface {

    /**
     * Creates a new account for a Company and adds it to {@link Bank#accounts}.
     *
     * @param accountHolder the account holder
     * @param pin account pin for the transactions
     * @param startingDeposit initial account balance
     * @return The account number for the newly created account.
     */
    long openAccount(AccountHolder accountHolder, String pin, double startingDeposit);

    /**
     * @param user the user trying to authenticate
     * @param accountNumber The account number for the transaction.
     * @param pin the account pin code
     * @return true if authentication was successful.
     */
    boolean authenticateUser(Person user, long accountNumber, String pin);

    /**
     * @param user the user trying to authenticate
     * @param accountNumber The account number for the transaction.
     * @param pin the account pin code
     * @return true if the user was added; otherwise, return false.
     */
    boolean addAuthenticateUser(Person user, long accountNumber, String pin);

    /**
     * @param accountNumber The account number for the transaction.
     * @return the balance of the account.
     */
    double getBalance(long accountNumber);

    /**
     * @param accountNumber The account number for the transaction.
     * @param amount The amount of money being deposited.
     */
    void credit(long accountNumber, double amount);

    /**
     * @param accountNumber The account number for the transaction.
     * @param amount the amount to be withdrawn
     * @return true if amount could be withdrawn; otherwise, return false.
     */
    boolean debit(long accountNumber, double amount);
}
