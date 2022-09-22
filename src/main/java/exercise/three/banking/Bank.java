package exercise.three.banking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
    private ConcurrentHashMap<Long, Account> accounts;

    private AtomicLong lastAccountNumber = new AtomicLong(0L);

    public Bank() {
        accounts = new ConcurrentHashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        final long accountNumber = lastAccountNumber.incrementAndGet();
        accounts.put(accountNumber, new CommercialAccount(company, accountNumber, pin, startingDeposit));
        return accountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        final long accountNumber = lastAccountNumber.incrementAndGet();
        accounts.put(accountNumber, new ConsumerAccount(person, accountNumber, pin, startingDeposit));
        return accountNumber;
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        return getAccount(accountNumber).validatePin(pin);
    }

    public double getBalance(Long accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    public void credit(Long accountNumber, double amount) {
        getAccount(accountNumber).creditAccount(amount);
    }

    public boolean debit(Long accountNumber, double amount) {
        return getAccount(accountNumber).debitAccount(amount);
    }
}
