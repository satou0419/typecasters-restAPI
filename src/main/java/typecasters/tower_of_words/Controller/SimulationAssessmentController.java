package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import typecasters.tower_of_words.Service.SimulationAssessmentService;

@RestController
public class SimulationAssessmentController {

    @Autowired
    SimulationAssessmentService simulationAssessmentService;


}
