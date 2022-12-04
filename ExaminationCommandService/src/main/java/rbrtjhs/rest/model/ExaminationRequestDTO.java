package rbrtjhs.rest.model;

import java.time.LocalDateTime;
import java.util.List;


public record ExaminationRequestDTO(String examinationID,
                                    String patientID,
                                    String doctorID,
                                    LocalDateTime time,
                                    String diagnosisID,
                                    String text,
                                    List<String> recipes) {
}
