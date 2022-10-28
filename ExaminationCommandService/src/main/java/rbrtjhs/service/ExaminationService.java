package rbrtjhs.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbrtjhs.aggregation.ExaminationAggregation;
import rbrtjhs.command.CreateExaminationCommand;
import rbrtjhs.entity.EventEntity;
import rbrtjhs.repository.EventRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ExaminationService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public void createExamination(String patientID, String doctorID, String text, String diagnosisID, List<String> recipes) throws JsonProcessingException, ExecutionException, InterruptedException {
        var command = CreateExaminationCommand.builder().patientID(patientID)
                .doctorID(doctorID)
                .text(text)
                .diagnosisID(diagnosisID)
                .recipes(recipes).build();
        var aggregation = new ExaminationAggregation();
        var createdExaminationEvent = aggregation.process(command);
        EventEntity eventEntity = new EventEntity(createdExaminationEvent);
        eventRepository.save(eventEntity);
        kafkaTemplate.send("examination-cqrs", aggregation.getAggregateID(), objectMapper.writeValueAsString(createdExaminationEvent)).get();
    }
}
