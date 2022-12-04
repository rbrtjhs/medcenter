package rbrtjhs.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import rbrtjhs.repository.EventRepository;
import rbrtjhs.service.ExaminationService;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminationServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ExaminationService examinationService;


    @BeforeEach
    public void prepare() throws Exception {
        ListenableFuture<SendResult<String, String>> responseFuture = mock(ListenableFuture.class);
        when(objectMapper.writeValueAsString(any())).thenReturn("");
        when(kafkaTemplate.send(anyString(), anyString(), anyString())).thenReturn(responseFuture);
        when(eventRepository.save(any())).thenReturn(null);
    }

    @Test
    public void createExamination_testCallingServices() throws Exception {
        var patientID = "patientID-123";
        var doctorID = "doctorID-456";
        var text = "examination description";
        var diagnosisID = "diagnosisID-789";
        var recipes = new LinkedList<String>();
        recipes.add("recipe1");
        recipes.add("recipe2");
        examinationService.createExamination(patientID, doctorID, text, diagnosisID, recipes);

        verify(eventRepository, times(1)).save(any());
        verify(objectMapper, times(1)).writeValueAsString(any());
        verify(kafkaTemplate, times(1)).send(anyString(), anyString(), anyString());
    }
}
