package rbrtjhs.event;

import lombok.AllArgsConstructor;
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

    public CreatedExaminationEvent(String aggregateID, List<Appointment> appointments) {
        this.aggregateID = aggregateID;
        data = new CreateExaminationEventData(appointments);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateExaminationEventData implements EventData {
        private List<Appointment> appointments;
    }
}
