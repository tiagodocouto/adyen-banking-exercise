package exercise.three.banking.bank;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import exercise.three.banking.account.AccountFactory;
import exercise.three.banking.account.AccountInterface;
import exercise.three.banking.account.CommercialAccount;
import exercise.three.banking.account.ConsumerAccount;
import exercise.three.banking.holder.AccountHolder;
import exercise.three.banking.holder.Person;

/**
 * Private Variables:
 * {@link #accounts}: ConcurrentHashMap<Long, AccountInterface>;
 * {@link #lastAccountNumber}: AtomicLong;
 */
public class Bank implements BankInterface {
    private final ConcurrentHashMap<Long, AccountInterface> accounts = new ConcurrentHashMap<>();

    private final AtomicLong lastAccountNumber = new AtomicLong(0L);

    /**
     * Fetch the account from the Bank database
     *
     * @param accountNumber the account number identifier
     * //@throws AccountNotFoundException if the account is not found
     * @return the account
     */
    private AccountInterface getAccount(final long accountNumber) {
        return accounts.get(accountNumber);
        // throw new AccountNotFoundException(accountNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long openAccount(final AccountHolder accountHolder, final String pin, final double startingDeposit) {
        final long accountNumber = lastAccountNumber.incrementAndGet();
        accounts.put(accountNumber, AccountFactory.createAccount(accountHolder, accountNumber, pin, startingDeposit));
        return accountNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean authenticateUser(final Person user, final long accountNumber, final String pin) {
        return switch (getAccount(accountNumber)) {
            case ConsumerAccount consumer -> consumer.validatePin(pin);
            case CommercialAccount commercial -> commercial.isAuthorizedUser(user) && commercial.validatePin(pin);
            default -> false;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAuthenticateUser(final Person user, final long accountNumber, final String pin) {
        if (getAccount(accountNumber) instanceof CommercialAccount commercial && commercial.validatePin(pin)) {
            commercial.addAuthorizedUser(user);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBalance(final long accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void credit(final long accountNumber, final double amount) {
        getAccount(accountNumber).creditAccount(amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean debit(final long accountNumber, final double amount) {
        return getAccount(accountNumber).debitAccount(amount);
    }
}
