package powerautomate.codeless.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import powerautomate.codeless.models.TestCaseRunningMonitor;

@Repository
public interface TestcaseRunningMonitorRepository extends MongoRepository<TestCaseRunningMonitor, String> {

}
