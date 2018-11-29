import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopierShould {

    class MockSource implements Source {

        private Queue<Character> chars = new ArrayDeque<>();

        public MockSource(String input) {
            input.chars().forEach(c -> chars.add((char) c));
        }

        @Override
        public char getChar() {
            return chars.remove();
        }
    }

    class DestinationSpy implements Destination {
        private List<Character> chars = new ArrayList<>();

        @Override
        public void setChar(char c) {
            chars.add(c);
        }

        private String chars() {
            return chars.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
        }
    }

    @Test
    public void not_copy_newline() {
        var source = new MockSource("\n");
        var destination = new DestinationSpy();

        new Copier(source, destination).copy();

        assertThat(destination.chars(), is(""));
    }

    @ParameterizedTest
    @ValueSource(strings={
            "abcde\n",
            "56789\n",
            "!\"£$%\n",
            "12\n345",
            "%%^^*\r&\0\n\0\b\r"
    })
    public void copy_characters_until_newline(String input) {
        var source = new MockSource(input);
        var destination = new DestinationSpy();

        new Copier(source, destination).copy();

        String expected = input.substring(0, input.indexOf("\n"));
        assertThat(destination.chars(), is(expected));
    }


}
