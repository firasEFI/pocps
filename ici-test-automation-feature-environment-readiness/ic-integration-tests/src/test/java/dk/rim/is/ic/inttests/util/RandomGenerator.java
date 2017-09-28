package dk.rim.is.ic.inttests.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    private static final long NUMBER_MAX_18_DIGITS_EXCLUSIVE = 1_000_000_000_000_000_000L;

    private static final long NUMBER_MAX_9_DIGITS_EXCLUSIVE = 1_000_000_000L;

    public static long randomLongMax18Digits() {
        return ThreadLocalRandom.current().nextLong(0, NUMBER_MAX_18_DIGITS_EXCLUSIVE);
    }

    public static long randomLongMax9Digits() {
        return ThreadLocalRandom.current().nextLong(0, NUMBER_MAX_9_DIGITS_EXCLUSIVE);
    }

}
