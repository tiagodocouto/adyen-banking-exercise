package exercise.three.banking.account;

import java.util.ArrayList;
import java.util.List;

import exercise.three.banking.holder.Company;
import exercise.three.banking.holder.Person;

/**
 * Account implementation for commercial (business) customers.
 * Private Variables:
 * {@link #authorizedUsers}: List<Person>
 */
public class CommercialAccount extends Account {
    private final List<Person> authorizedUsers;

    /**
     * Creates a new commercial account for the company
     *
     * @param company the company that owns the account
     * @param accountNumber the unique account number
     * @param pin the account pin code
     * @param startingDeposit the initial account balance
     */
    public CommercialAccount(final Company company,
                             final long accountNumber,
                             final String pin,
                             final double startingDeposit) {
        super(company, accountNumber, pin, startingDeposit);
        authorizedUsers = new ArrayList<>();
    }

    /**
     * @param person The authorized user to add to the account.
     */
    public void addAuthorizedUser(final Person person) {
        authorizedUsers.add(person);
    }

    /**
     * @param person person to has access to the account
     * @return true if person matches an authorized user in {@link #authorizedUsers}; otherwise, false.
     */
    public boolean isAuthorizedUser(final Person person) {
        return authorizedUsers.contains(person);
    }
}
