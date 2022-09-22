package exercise.three.banking.account;

import java.util.concurrent.atomic.AtomicReference;

import exercise.three.banking.encryption.VeryBasicAuth;
import exercise.three.banking.holder.AccountHolder;
import exercise.three.banking.holder.Company;
import exercise.three.banking.holder.Person;

/**
 * Abstract bank account class.<br>
 * <br>
 * <p>
 * Private Variables:<br>
 * {@link #accountHolder}: AccountHolder<br>
 * {@link #accountNumber}: Long<br>
 * {@link #pin}: int<br>
 * {@link #balance}: double
 */
public abstract class Account implements AccountInterface {
    private final AccountHolder accountHolder;

    private final Long accountNumber;

    private final String pin;

    private final AtomicReference<Double> balance;

    /**
     * Creates a new Account
     *
     * @param accountHolder the account holder: {@link Company} or {@link Person}
     * @param accountNumber the unique account number
     * @param pin the account pin
     * @param balance the initial account balance
     */
    protected Account(final AccountHolder accountHolder,
                      final Long accountNumber,
                      final String pin,
                      final double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = VeryBasicAuth.getInstance().hash(pin);
        this.balance = new AtomicReference<>(balance);
    }

    /**
     * Returns the account holder
     *
     * @return the account holder
     */
    @Override
    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    /**
     * Returns the account holder
     *
     * @return the account holder
     */
    @Override
    public boolean validatePin(final String attemptedPin) {
        return VeryBasicAuth.getInstance().verify(pin, attemptedPin);
    }

    /**
     * Get the current account balance
     *
     * @return the account balance
     */
    @Override
    public double getBalance() {
        return balance.get();
    }

    /**
     * Get the account number
     *
     * @return the account number
     */
    @Override
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Credit balance to the account
     *
     * @param amount amount to credit into the account
     */
    @Override
    public void creditAccount(final double amount) {
        balance.accumulateAndGet(amount, Double::sum);
    }

    /**
     * Withdraw balance from the account
     *
     * @param amount the amount to be withdrawn
     * @return true if amount could be withdrawn; otherwise, return false.
     */
    @Override
    public boolean debitAccount(final double amount) {
        if (balance.get() - amount >= 0) {
            balance.accumulateAndGet(amount, (aDouble, aDouble2) -> aDouble - aDouble2);
            return true;
        }
        return false;
    }
}
