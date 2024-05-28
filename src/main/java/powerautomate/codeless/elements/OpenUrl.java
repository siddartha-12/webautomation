package powerautomate.codeless.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
@Getter
@Setter
public class OpenUrl implements Element{
    private String value;
    @Override
    public boolean execute(WebDriver webDriver) {
        try {
            webDriver.get(value);
            return true;
        }
        catch (Exception exception){

        }
        return false;
    }
}
