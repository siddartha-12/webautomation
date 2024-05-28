package powerautomate.codeless.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import powerautomate.codeless.models.ElementType;
import powerautomate.codeless.selectors.Selector;

import java.util.List;
@Getter
@AllArgsConstructor
public class ElementRequest {
    @NonNull
    public List<SelectorRequest> selectorRequests;
    @NonNull
    public ElementType elementType;
}
