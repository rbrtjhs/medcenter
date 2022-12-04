package rbrtjhs.aggregation;

import java.time.LocalDateTime;
import java.util.List;

public record Appointment(String patientID,
                          String doctorID,
                          LocalDateTime time,
                          String diagnosisID,
                          String text,
                          List<String> recipes) {

}
