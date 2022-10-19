package rbrtjhs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbrtjhs.rest.model.ExaminationRequestDTO;
import rbrtjhs.service.ExaminationService;

@RestController
@RequestMapping("/examination")
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @PostMapping
    public ResponseEntity createExamination(@RequestBody ExaminationRequestDTO examinationRequestDTO) {
        examinationService.createExamination(examinationRequestDTO.getPatientID(),
                examinationRequestDTO.getDoctorID(),
                examinationRequestDTO.getText(),
                examinationRequestDTO.getDiagnosisID(),
                examinationRequestDTO.getRecipes());
        return ResponseEntity.ok().build();
    }
}
