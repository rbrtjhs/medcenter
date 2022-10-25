package rbrtjhs.command;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateExaminationCommand implements ExaminationCommand {
    private String patientID;
    private String doctorID;
    private String diagnosisID;
    private String text;
    private List<String> recipes;
}
