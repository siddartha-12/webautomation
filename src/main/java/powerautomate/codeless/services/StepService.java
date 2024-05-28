package powerautomate.codeless.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerautomate.codeless.exceptions.StepNotFound;
import powerautomate.codeless.models.Step;
import powerautomate.codeless.respositories.StepRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StepService {

    @Autowired
    StepRepository stepRepository;

    public String addStep(Step step){
        step = stepRepository.save(step);
        return step.getId();
    }

    public Step updateStep(Step step){
        try {
            Step oldStep = getStep(step.getId());
            step = stepRepository.save(step);
        }
        catch (StepNotFound stepNotFound){
            throw new StepNotFound("Step Not Found");
        }
        return step;
    }
    public String deleteStep(String id){
        stepRepository.deleteById(id);
        return id;
    }
    public Step getStep(String id){
        Optional<Step> step = stepRepository.findById(id);
        if(step.isEmpty()){
            throw new StepNotFound("Step not Found");
        }
        return step.get();
    }
    public List<Step> getAllSteps(){
        return stepRepository.findAll();
    }

}
