package EvaluationBureau.service;

import EvaluationBureau.jpa.UserRepository;
import EvaluationBureau.service.inter.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements BaseService {

    private final UserRepository repository;


}
