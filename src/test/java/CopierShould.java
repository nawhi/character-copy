import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopierShould {

    class MockSource implements Source {

        private char charToReturn;

        public MockSource(char c) {
            this.charToReturn = c;
        }

        @Override
        public char getChar() {
            return charToReturn;
        }
    }

    class DestinationSpy implements Destination {
        private List<Character> chars = new ArrayList<>();

        @Override
        public void setChar(char c) {
            chars.add(c);
        }

        private List<Character> chars() {
            return chars;
        }
    }

    @ParameterizedTest
    @ValueSource(strings={"a", "5", "#"})
    public void copy_single_characters(String input) {
        final char testChar = input.charAt(0);

        var source = new MockSource(testChar);
        var destination = new DestinationSpy();

        new Copier(source, destination).copy();

        assertThat(destination.chars().get(0), is(testChar));
    }

    @Test
    public void not_copy_newline_from_source_to_destination() {
        final char testChar = '\n';
        var source = new MockSource(testChar);
        var destination = new DestinationSpy();

        new Copier(source, destination).copy();

        assertThat(destination.chars(), is(emptyList()));
    }


}
