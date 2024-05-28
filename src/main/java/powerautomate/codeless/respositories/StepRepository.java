package powerautomate.codeless.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import powerautomate.codeless.models.Step;
@Repository
public interface StepRepository extends MongoRepository<Step,String> {
}
