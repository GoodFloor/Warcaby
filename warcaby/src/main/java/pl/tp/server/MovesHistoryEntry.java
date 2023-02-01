package pl.tp.server;

public class MovesHistoryEntry {
    private MovesHistory parent;
    private int ordinal;
    private String source;
    private String destination;
    public MovesHistoryEntry(int ordinal, String source, String destination, MovesHistory parent) {
        this.ordinal = ordinal;
        this.source = source;
        this.destination = destination;
        this.parent = parent;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
}
