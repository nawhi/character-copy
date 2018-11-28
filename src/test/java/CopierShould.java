import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopierShould {

    class MockSource implements Source {

        private char charToReturn;

        @Override
        public char getChar() {
            return charToReturn;
        }

        public Source using(char c) {
            this.charToReturn = c;
            return this;
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

    @Test
    public void copy_character_from_source_to_destination() {
        final char testChar = 'X';
        var source = new MockSource().using(testChar);
        var destination = new DestinationSpy();

        new Copier(source, destination).copy();

        assertThat(destination.chars().get(0), is(testChar));
    }
}
