package rbrtjhs.aggregation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rbrtjhs.command.AddAppointmentCommand;
import rbrtjhs.command.CreateExaminationCommand;
import rbrtjhs.core.Event;
import rbrtjhs.entity.EventEntity;
import rbrtjhs.event.CreatedExaminationEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ExaminationAggregation implements Serializable {
    @Getter
    private String aggregateID;
    private List<Appointment> appointments = new LinkedList<>();

    public Event process(CreateExaminationCommand command) {
        var appointment = Appointment.builder()
                .doctorID(command.getDoctorID())
                .patientID(command.getPatientID())
                .diagnosisID(command.getDiagnosisID())
                .text(command.getText())
                .time(LocalDateTime.now())
                .recipes(command.getRecipes())
                .build();
        this.appointments.add(appointment);
        this.aggregateID = UUID.randomUUID().toString();
        return new CreatedExaminationEvent(this.aggregateID, this.appointments);
    }

    public EventEntity process(AddAppointmentCommand command) {
        throw new UnsupportedOperationException();
    }
}
