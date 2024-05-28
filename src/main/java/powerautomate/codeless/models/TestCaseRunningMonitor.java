package powerautomate.codeless.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Document(collection = "runningcases")
public class TestCaseRunningMonitor {
    private String createdTime;
    private String testCaseId;
    @Id
    private String runId;
    @Setter private List<String> executedSteps;
    @Setter private TestCaseRunningStatus testCaseRunningStatus;
}

