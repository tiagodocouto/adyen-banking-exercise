package exercise.three.banking;

import exercise.three.banking.account.AccountTest;
import exercise.three.banking.account.CommercialAccountTest;
import exercise.three.banking.holder.CompanyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        SampleTest.class,
        HiddenTest.class,
        ConcurrentTest.class,
        AccountTest.class,
        CommercialAccountTest.class,
        CompanyTest.class
})
public class UnitTestSuite {
}
