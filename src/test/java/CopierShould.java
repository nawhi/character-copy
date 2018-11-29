import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CopierShould {

    @Test
    public void call_setChar_on_dest() {
        final char testChar = 'c';

        Source source = mock(Source.class);
        when(source.getChar()).thenReturn(testChar);

        Destination destination = mock(Destination.class);

        new Copier(source, destination).copy();

        verify(destination).setChar(testChar);

    }
}
