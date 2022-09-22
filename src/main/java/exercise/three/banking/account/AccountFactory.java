package exercise.three.banking.account;

import exercise.three.banking.holder.AccountHolder;
import exercise.three.banking.holder.Company;
import exercise.three.banking.holder.Person;

public class AccountFactory {
    private AccountFactory() {
    }

    /**
     * @param holder account holder
     * @param number account number
     * @param pin account pin
     * @param startingDeposit initial account balance
     * @return an implementation of Account interface based on account holder type
     */
    public static AccountInterface createAccount(final AccountHolder holder, final long number, final String pin, final double startingDeposit) {
        return switch (holder) {
            case Person p -> new ConsumerAccount(p, number, pin, startingDeposit);
            case Company c -> new CommercialAccount(c, number, pin, startingDeposit);
            default -> throw new IllegalStateException("Unexpected value: " + holder);
        };
    }
}
