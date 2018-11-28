import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopierShould {

    class MockDestination implements Destination {
        private boolean called;

        @Override
        public void setChar(char c) {
            called = true;
        }

        public boolean setCharWasCalled() {
            return called;
        }
    }

    @Test
    public void calls_setChar_on_destination() {
        var mockDestination = new MockDestination();
        var dummySource = new Source() {
            @Override public char getChar() { return '\0'; }
        };
        var copier = new Copier(dummySource, mockDestination);
        copier.copy();
        assertThat(mockDestination.setCharWasCalled(), is(true));
    }
}
