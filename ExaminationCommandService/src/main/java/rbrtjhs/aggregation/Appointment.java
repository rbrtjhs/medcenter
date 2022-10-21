package rbrtjhs.aggregation;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Appointment {
    private String patientID;
    private String doctorID;
    private LocalDateTime time;
    private String diagnosisID;
    private String text;
    private List<String> recipes;
}
