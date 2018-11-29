import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CopierShould {

    @Test
    public void copy_single_character() {
        final char testChar = 'c';

        Source source = mock(Source.class);
        when(source.getChar()).thenReturn(testChar);

        Destination destination = mock(Destination.class);

        new Copier(source, destination).copy();

        verify(destination).setChar(testChar);
    }

    @Test
    public void not_copy_newline() {
        final char testChar = '\n';

        Source source = mock(Source.class);
        when(source.getChar()).thenReturn(testChar);

        Destination destination = mock(Destination.class);

        new Copier(source, destination).copy();

        verify(destination, never()).setChar(testChar);
    }
}
