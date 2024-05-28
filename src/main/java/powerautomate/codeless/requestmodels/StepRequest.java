package powerautomate.codeless.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import powerautomate.codeless.elements.Element;


@Getter
@AllArgsConstructor
public class StepRequest {
    @NonNull
    private String title;
    @NonNull
    private ElementRequest elementRequest;
    private String Value;
}
