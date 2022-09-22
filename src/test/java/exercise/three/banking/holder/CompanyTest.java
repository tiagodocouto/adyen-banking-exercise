package exercise.three.banking.holder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompanyTest {
    @Test
    public void givenNewCompanyExpectToPropertiesToBeSet() {
        //given
        final var company = new Company("Company 1", 1);

        //when
        //then
        assertEquals("Company 1", company.getCompanyName());
        assertEquals(1, company.getIdNumber());
    }
}
