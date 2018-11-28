public class Copier {

    private Destination destination;

    public Copier(Source source, Destination destination) {
        this.destination = destination;
    }

    public void copy() {
        destination.setChar('\0');
    }
}
