package rbrtjhs.aggregation;

import com.eventstore.dbclient.EventData;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rbrtjhs.command.AddAppointmentCommand;
import rbrtjhs.command.CreateExaminationCommand;
import rbrtjhs.util.ConvertToBinary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor
public class ExaminationAggregation implements Serializable {
    public static final String NAME = ExaminationAggregation.class.getSimpleName().toUpperCase();

    private String examinationID;
    private String patientID;
    private List<Appointment> appointments = new LinkedList<>();

    public EventData process(CreateExaminationCommand command) {
        this.examinationID = UUID.randomUUID().toString();
        this.patientID = command.getPatientID();
        var appointment = Appointment.builder()
                .diagnosisID(command.getDiagnosisID())
                .text(command.getText())
                .time(LocalDateTime.now())
                .recipes(command.getRecipes())
                .build();
        this.appointments.add(appointment);
        var bytes = ConvertToBinary.convert(this);
        return EventData.builderAsBinary(command.getName(), bytes).build();
    }

    public EventData process(AddAppointmentCommand command) {
        throw new UnsupportedOperationException();
    }
}
