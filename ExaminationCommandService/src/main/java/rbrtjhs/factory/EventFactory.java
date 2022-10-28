package rbrtjhs.factory;

import rbrtjhs.core.Event;
import rbrtjhs.entity.EventEntity;

import java.util.List;

public class EventFactory {

    public static List<EventEntity> events(List<Event> events) {
        return events.stream().map(EventEntity::new).toList();
    }
}
