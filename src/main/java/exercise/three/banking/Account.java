package exercise.three.banking;

import java.util.concurrent.atomic.AtomicReference;

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
public abstract class Account {
    private AccountHolder accountHolder;

    private Long accountNumber;

    private int pin;

    private AtomicReference<Double> balance;

    public Account(AccountHolder accountHolder, Long accountNumber, int pin, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = new AtomicReference<>(balance);
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public boolean validatePin(int attemptedPin) {
        return pin == attemptedPin;
    }

    public double getBalance() {
        return balance.get();
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void creditAccount(double amount) {
        balance.accumulateAndGet(amount, Double::sum);
    }

    public boolean debitAccount(double amount) {
        if (balance.get() - amount >= 0) {
            balance.accumulateAndGet(amount, (aDouble, aDouble2) -> aDouble - aDouble2);
            return true;
        }
        return false;
    }
}
