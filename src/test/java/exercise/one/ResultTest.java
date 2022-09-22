package exercise.one;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ResultTest {
    @Test
    public void testCardNumberWhenIsInRangeOfOneShouldBeVisa() {
        final String cardNumber = "4111111111111111";
        List<Result.BinRange> binRanges = Arrays.asList(
                new Result.BinRange("400000000000", "499999999999", "visa"),
                new Result.BinRange("500000000000", "599999999999", "mc")
        );
        Result.CardTypeCache cache = Result.buildCache(binRanges);
        Assert.assertEquals("visa", cache.get(cardNumber));
    }
}
