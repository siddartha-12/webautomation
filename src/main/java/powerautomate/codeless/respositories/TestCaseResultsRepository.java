package powerautomate.codeless.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import powerautomate.codeless.models.TestCaseResult;

@Repository
public interface TestCaseResultsRepository extends MongoRepository<TestCaseResult, String> {
}
