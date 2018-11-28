import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CopierShould {

    class DestinationStub implements Destination {
        @Override
        public void setChar(char c) {}
    }

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

    class SourceSpy implements Source {
        private boolean called;

        @Override
        public char getChar() {
            called = true;
            return 0;
        }

        public boolean getCharWasCalled() {
            return called;
        }
    }

    @Test
    public void calls_setChar_on_destination() {
        var destinationSpy = new DestinationSpy();
        var sourceStub = new SourceStub();

        new Copier(sourceStub, destinationSpy).copy();

        assertThat(destinationSpy.setCharWasCalled(), is(true));
    }

    @Test
    public void calls_getChar_on_source() {
        var destinationStub = new DestinationStub();
        var sourceSpy = new SourceSpy();

        new Copier(sourceSpy, destinationStub).copy();

        assertThat(sourceSpy.getCharWasCalled(), is(true));
    }
}
