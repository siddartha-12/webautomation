package powerautomate.codeless.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import powerautomate.codeless.elements.Element;

@Builder
@Getter
@Document(collection = "steps")
public class Step {

    @Id
    private String id;
    @Setter private String title;
    @Setter private Element element;
    @Setter  private String Value;
}
