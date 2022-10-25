package rbrtjhs.command;

import lombok.Data;

import java.util.List;

@Data
public class AddAppointmentCommand implements ExaminationCommand {
    private String examinationID;
    private String doctorID;
    private String diagnosisID;
    private String text;
    private List<String> recipes;
}
