package exercise.three.banking.account;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import exercise.three.banking.holder.Company;
import exercise.three.banking.holder.Person;
import org.junit.Test;

public class CommercialAccountTest {
    @Test
    public void givenNewCommercialAccountExpectPropertiesToBeSet() {
        //given
        final var company = new Company("Tiago LTDA.", 2);
        final var person = new Person("One", "Person", 3);
        final var otherPerson = new Person("Other", "Guy", 9);
        final var account = AccountFactory.createAccount(company, 1, "1", 0);

        //when
        assertThat(account, instanceOf(CommercialAccount.class));
        final var commercialAccount = (CommercialAccount) account;
        commercialAccount.addAuthorizedUser(person);

        //then
        assertTrue(commercialAccount.isAuthorizedUser(person));
        assertFalse(commercialAccount.isAuthorizedUser(otherPerson));
    }
}
