package powerautomate.codeless.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Getter
@Builder
@Document(collection = "testcaseresults")
public class TestCaseResult {

    @Id
    public String testCaseId;
    @Setter public TestCaseStatus testCaseStatus;
    @Setter  public String lastRun;
    @Setter public String lastPassed;
    @Setter public Integer totalRuns;
    @Setter public Integer totalRunsPassed;

}
