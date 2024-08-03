package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import typecasters.tower_of_words.Service.SimulationWeightedScoreService;

@RestController
public class SimulationWeightedScoreController {

    @Autowired
    SimulationWeightedScoreService simulationWeightedScoreService;


}
