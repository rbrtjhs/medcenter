package rbrtjhs.factory;

import rbrtjhs.core.Event;
import rbrtjhs.entity.EventEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EventFactory {
    public static EventEntity event(Event event) {
        return new EventEntity(event.getClass().getSimpleName(), event.getAggregateID(), event.getData());
    }

    public static List<EventEntity> events(List<Event> events) {
        return events.stream().map(x -> event(x)).collect(Collectors.toUnmodifiableList());
    }
}
