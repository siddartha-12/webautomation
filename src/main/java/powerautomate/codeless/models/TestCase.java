package powerautomate.codeless.models;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Builder
@Getter
@Document(collection = "testcases")
public class TestCase {
    private String id;
    @Setter private String description;
    @Valid
    @Setter private List<StepProperties> steps;
    @Setter private String monitorId;
    public synchronized void setMonitorId(String id){
        this.monitorId = id;
    }
}
