public class Copier {

    private Source source;
    private Destination destination;

    public Copier(Source source, Destination destination) {
        this.source = source;
        this.destination = destination;
    }

    public void copy() {
        char c;
        if ((c = source.getChar()) != '\n')
            destination.setChar(c);
    }
}
