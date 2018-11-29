import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CopierShould {

    private Source source;
    private Destination destination;

    @BeforeEach
    void setUp() {
        source = mock(Source.class);
        destination = mock(Destination.class);
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
        when(source.getChar()).thenReturn('a', 'b', 'c', 'c', 'd', '\n', 'e', 'f');

        new Copier(source, destination).copy();

        verify(destination).setChar('a');
        verify(destination).setChar('b');
        verify(destination, times(2)).setChar('c');
        verify(destination).setChar('d');
    }
}