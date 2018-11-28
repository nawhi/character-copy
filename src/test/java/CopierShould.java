import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopierShould {

    class DestinationSpy implements Destination {
        private boolean called;

        @Override
        public void setChar(char c) {
            called = true;
        }

        public boolean setCharWasCalled() {
            return called;
        }
    }

    class SourceStub implements Source {
        @Override
        public char getChar() {
            return 0;
        }
    }

    @Test
    public void calls_setChar_on_destination() {
        var destinationSpy = new DestinationSpy();
        var sourceStub = new SourceStub();
        var copier = new Copier(sourceStub, destinationSpy);
        copier.copy();
        assertThat(destinationSpy.setCharWasCalled(), is(true));
    }
}
