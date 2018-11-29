import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public class CopierShould {

    private Source source;
    private Destination destination;

    @BeforeEach
    void setUp() {
        source = mock(Source.class);
        destination = mock(Destination.class, withSettings().verboseLogging());
    }

    @Test
    public void not_copy_newline() {
        final char testChar = '\n';

        when(source.getChar()).thenReturn(testChar);

        new Copier(source, destination).copy();

        verify(destination, never()).setChar(testChar);
    }

    @Test
    public void copy_characters_until_newline() {
        String input = "abc\n";
        String expectedOutput = input.substring(0, input.indexOf('\n'));

        when(source.getChar()).thenAnswer(new Answer() {
            int i = 0;

            @Override
            public Object answer(InvocationOnMock invocation) {
                return input.charAt(i++);
            }
        });

        new Copier(source, destination).copy();

        var order = inOrder(destination);
        for (char c: expectedOutput.toCharArray()) {
            order.verify(destination, times(1)).setChar(c);
        }
    }

    class Foo {
        void call(int i) {}
    }

    @Test
    public void how_does_mockito_work() {
        var obj = mock(Foo.class);

        obj.call(0);
        obj.call(1);
        obj.call(2);

        for (int i = 0; i < 3; i++) {
            verify(obj).call(i);
        }




    }
}
