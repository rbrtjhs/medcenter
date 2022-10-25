package rbrtjhs.core;

public interface Event {
    String getAggregateID();
    EventData getData();
}
