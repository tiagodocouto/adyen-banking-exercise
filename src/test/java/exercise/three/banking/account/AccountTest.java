package exercise.three.banking.account;

import static org.junit.Assert.assertEquals;

import exercise.three.banking.holder.AccountHolder;
import exercise.three.banking.holder.Person;
import org.junit.Test;

public class AccountTest {
    @Test
    public void givenNewAccountExpectToPropertiesToBeSet() {
        //given
        final var person = new Person("Tiago", "Couto", 100223);
        final var account = AccountFactory.createAccount(person, 1, "1", 0);

        //when
        //then
        assertEquals(person, account.getAccountHolder());
        assertEquals(0.0, account.getBalance(), 0);
        assertEquals(1, account.getAccountNumber());
    }

    @Test(expected = IllegalStateException.class)
    public void givenNullAccountHolderExpectToThrowIllegalStateException() {
        //given
        AccountFactory.createAccount(new AccountHolder(1) {
        }, 1, "1", 0);
    }
}
