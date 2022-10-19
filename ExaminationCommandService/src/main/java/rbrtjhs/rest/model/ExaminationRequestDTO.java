package rbrtjhs.rest.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class ExaminationRequestDTO {
    private String examinationID;
    private String patientID;
    private String doctorID;
    private LocalDateTime time;
    private String diagnosisID;
    private String text;
    private List<String> recipes;
}
