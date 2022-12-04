package rbrtjhs.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbrtjhs.aggregation.ExaminationAggregation;
import rbrtjhs.command.CreateExaminationCommand;
import rbrtjhs.entity.EventEntity;
import rbrtjhs.repository.EventRepository;
import rbrtjhs.util.KafkaTopics;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ExaminationService {

    private EventRepository eventRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public ExaminationService(EventRepository eventRepository, KafkaTemplate kafkaTemplate, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public EventEntity createExamination(String patientID, String doctorID, String text, String diagnosisID, List<String> recipes) throws JsonProcessingException, ExecutionException, InterruptedException {
        var command = CreateExaminationCommand.builder().patientID(patientID)
                .doctorID(doctorID)
                .text(text)
                .diagnosisID(diagnosisID)
                .recipes(recipes).build();
        var aggregation = new ExaminationAggregation();
        var createdExaminationEvent = aggregation.process(command);
        var eventEntity = new EventEntity(createdExaminationEvent);
        eventEntity = eventRepository.save(eventEntity);
        kafkaTemplate.send(KafkaTopics.EXAMINATION_CQRS, aggregation.getAggregateID(), objectMapper.writeValueAsString(createdExaminationEvent)).get();
        return eventEntity;
    }
}
