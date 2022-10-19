package rbrtjhs.service;


import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbrtjhs.aggregation.ExaminationAggregation;
import rbrtjhs.command.CreateExaminationCommand;

import java.util.LinkedList;
import java.util.List;

@Service
public class ExaminationService {

    @Autowired
    private EventStoreDBClient eventstore;

    public void createExamination(String patientID, String doctorID, String text, String diagnosisID, List<String> recipes) {
        var command = CreateExaminationCommand.builder().patientID(patientID)
                .doctorID(doctorID)
                .text(text)
                .diagnosisID(diagnosisID)
                .recipes(recipes).build();
        ExaminationAggregation aggregation = new ExaminationAggregation();
        var event = aggregation.process(command);
        eventstore.appendToStream(ExaminationAggregation.NAME, event);
    }
}
