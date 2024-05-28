package powerautomate.codeless.requestmodels;

import lombok.NonNull;
import powerautomate.codeless.models.SelectorType;


public class SelectorRequest {
    @NonNull
    public SelectorType selectorType;
    @NonNull
    public String value;
}
