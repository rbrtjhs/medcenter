package rbrtjhs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbrtjhs.aggregation.ExaminationAggregation;
import rbrtjhs.command.CreateExaminationCommand;
import rbrtjhs.repository.EventRepository;

import java.util.List;

@Service
public class ExaminationService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void createExamination(String patientID, String doctorID, String text, String diagnosisID, List<String> recipes) {
        var command = CreateExaminationCommand.builder().patientID(patientID)
                .doctorID(doctorID)
                .text(text)
                .diagnosisID(diagnosisID)
                .recipes(recipes).build();
        var aggregation = new ExaminationAggregation();
        var createdExaminationEvent = aggregation.process(command);
        eventRepository.save(createdExaminationEvent);
    }
}
