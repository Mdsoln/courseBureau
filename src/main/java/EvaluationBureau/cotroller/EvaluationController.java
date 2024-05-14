package EvaluationBureau.cotroller;


import EvaluationBureau.service.EvaluationServiceImpl;
import EvaluationBureau.service.inter.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/evaluation")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationServiceImpl evaluationService;

}
