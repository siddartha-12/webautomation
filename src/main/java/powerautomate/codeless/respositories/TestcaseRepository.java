package powerautomate.codeless.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import powerautomate.codeless.models.TestCase;

@Repository
public interface TestcaseRepository extends MongoRepository<TestCase,String> {
}
