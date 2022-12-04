package rbrtjhs.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import rbrtjhs.aggregation.Appointment;
import rbrtjhs.core.Event;
import rbrtjhs.core.EventData;

import java.util.List;


@Data
@NoArgsConstructor
public class CreatedExaminationEvent implements Event {
    private String aggregateID;
    private EventData data;

    private final String name = CreatedExaminationEvent.class.getSimpleName();

    public CreatedExaminationEvent(String aggregateID, List<Appointment> appointments) {
        this.aggregateID = aggregateID;
        this.data = new CreateExaminationEventData(appointments);
    }

    public record CreateExaminationEventData(List<Appointment> appointments) implements EventData {

    }
}
