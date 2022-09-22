package exercise.three.banking.transaction;

import exercise.three.banking.bank.Bank;
import exercise.three.banking.exceptions.NotAuthenticatedException;
import exercise.three.banking.holder.Person;

/**
 * Private Variables:<br>
 * {@link #accountNumber}: Long<br>
 * {@link #bank}: Bank<br>
 */
public class Transaction implements TransactionInterface {
    private final Long accountNumber;

    private final Bank bank;

    /**
     * @param bank The bank where the account is housed.
     * @param accountNumber The customer's account number.
     * @param attemptedPin The PIN entered by the customer.
     * @throws NotAuthenticatedException Account validation failed.
     */
    public Transaction(final Bank bank, final Long accountNumber, final String attemptedPin) throws NotAuthenticatedException {
        this(bank, null, accountNumber, attemptedPin);
    }

    /**
     * @param bank The bank where the account is housed.
     * @param user The user trying to authenticate
     * @param accountNumber The customer's account number.
     * @param attemptedPin The PIN entered by the customer.
     * @throws NotAuthenticatedException Account validation failed.
     */
    public Transaction(final Bank bank, final Person user, final Long accountNumber, final String attemptedPin) throws NotAuthenticatedException {
        this.bank = bank;
        this.accountNumber = accountNumber;
        if (!bank.authenticateUser(user, accountNumber, attemptedPin)) {
            throw new NotAuthenticatedException();
        }
    }

    public double getBalance() {
        return bank.getBalance(accountNumber);
    }

    public void credit(double amount) {
        bank.credit(accountNumber, amount);
    }

    public boolean debit(double amount) {
        return bank.debit(accountNumber, amount);
    }
}
