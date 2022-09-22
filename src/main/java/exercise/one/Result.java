package exercise.one;

import java.util.List;

class Result {

    /**
     * @param binRanges the list of card bin ranges to build a cache from.
     * @return an implementation of CardTypeCache.
     */
    public static CardTypeCache buildCache(List<BinRange> binRanges) {
        return cardNumber -> binRanges.parallelStream()
                .filter(binRange -> binRange.valid(cardNumber))
                .map(BinRange::cardType)
                .findFirst()
                .orElse(null);
    }

    interface CardTypeCache {
        /**
         * @param cardNumber 12 to 23 digit card number.
         * @return the card type for this cardNumber or null if the card number does not
         * fall into any valid bin ranges.
         */
        String get(String cardNumber);
    }

    /**
     * An entity to hold bin range details. A bin range is a pair of 12 digit numbers that
     * mark the boundaries of the range which is maped to other bin range properties such
     * as a card type. The range boundaries are inclusive.
     */
    static final class BinRange {
        private final long start;

        private final long end;

        private final String cardType;

        private final int length;

        BinRange(String start, String end, String cardType) {
            this.length = start.length();
            this.start = Long.parseLong(start);
            this.end = Long.parseLong(end);
            this.cardType = cardType;
        }

        public String cardType() {
            return cardType;
        }

        public boolean valid(final String cardNumber) {
            final long card = Long.parseLong(cardNumber.substring(0, length));
            return start <= card && card <= end;
        }
    }
}
