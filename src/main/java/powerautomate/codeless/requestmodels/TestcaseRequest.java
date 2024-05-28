package powerautomate.codeless.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import powerautomate.codeless.models.StepProperties;

import java.util.List;

@AllArgsConstructor
@Getter
public class TestcaseRequest {
    public String description;
    public List<StepProperties> steps;
}
