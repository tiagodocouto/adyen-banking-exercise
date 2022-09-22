package exercise.three.banking.account;

import exercise.three.banking.holder.Person;

public class ConsumerAccount extends Account {
    public ConsumerAccount(Person person, Long accountNumber, String pin, double currentBalance) {
        super(person, accountNumber, pin, currentBalance);
    }
}
