package dk.rim.is.ic.inttests;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class RegexMatcher extends TypeSafeMatcher<String> {

    private final String regex;

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    @Override
    public void describeTo(final Description description) {
        String text = String.format("matches regular expression='%s'", regex);
        description.appendText(text);
    }

    @Override
    public boolean matchesSafely(final String string) {
        return string.matches(regex);
    }

    public static RegexMatcher matchesRegex(final String regex) {
        return new RegexMatcher(regex);
    }
}
