package exercise.three.banking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import exercise.three.banking.bank.Bank;
import exercise.three.banking.holder.Person;
import exercise.three.banking.transaction.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentTest {

    ExecutorService service;

    @Before
    public void setUp() {
        service = Executors.newFixedThreadPool(8);
    }

    @Test
    public void concurrentDebitForCharity() throws InterruptedException {
        final int amountOfCharities = 8000;
        final int amount = 10;

        final Bank bank = new Bank();
        final Person person = new Person("Charitable", "Guy", 12345);
        final long accountNumber = bank.openAccount(person, "12345", amount * amountOfCharities);

        final CountDownLatch latch = new CountDownLatch(amountOfCharities);

        for (int i = 0; i < amountOfCharities; i++) {
            service.submit(() -> {
                if (bank.authenticateUser(null, accountNumber, "12345")) {
                    bank.debit(accountNumber, amount);
                }

                latch.countDown();
            });
        }

        assertTrue("Timed out waiting for debits!", latch.await(5, TimeUnit.SECONDS));

        final double balance = bank.getBalance(accountNumber);
        assertEquals((person.getFirstName() + " " + person.getLastName())
                             + " does not have an empty account after giving "
                             + amountOfCharities + " donations of " + amount + "EUR!", 0d, balance, 0.0);
    }

    @Test
    public void concurrentConsumerAccounts() {
        final Bank bank = new Bank();
        final int maxAccounts = 10_000;

        final List<AccountIdent> accounts = IntStream.range(0, maxAccounts).parallel().mapToObj(identifier -> {
            final Person accountHolder = new Person("person-" + identifier, "test", identifier);
            return new AccountIdent(String.valueOf(identifier), bank.openAccount(accountHolder, String.valueOf(identifier), identifier));
        }).toList();

        for (long i = 1; i <= maxAccounts; i++) {
            final long lookFor = i;
            assertTrue("Account id " + i + " was not found!",
                       accounts.stream().anyMatch(ident -> ident.accountId == lookFor));
        }

        for (AccountIdent ident : accounts) {
            // make a transaction on every account
            Transaction transaction = null;
            try {
                transaction = new Transaction(bank, ident.accountId, ident.identifier);
            } catch (Exception e) {
                fail("Could not authenticate account " + ident.accountId + " with pin: " + ident.identifier);
            }

            // remove balance
            assertTrue(transaction.debit(Double.parseDouble(ident.identifier)));

            final double balance = bank.getBalance(ident.accountId);
            assertEquals(0d, balance, 0.0);
        }
    }

    @After
    public void teardown() throws InterruptedException {
        service.shutdown();
        if (!service.awaitTermination(5, TimeUnit.SECONDS)) {
            service.shutdownNow();
        }
    }

    private static class AccountIdent {
        String identifier;

        Long accountId;

        AccountIdent(String identifier, Long accountId) {
            this.identifier = identifier;
            this.accountId = accountId;
        }
    }
}
