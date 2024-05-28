package powerautomate.codeless.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class SwitchToTab implements Element{
    private String value;
    @Override
    public boolean execute(WebDriver webDriver) {
        int targetTab = Integer.valueOf(value);
        Set<String> windowHandles = webDriver.getWindowHandles();
        if (targetTab>windowHandles.size()){
            return false;
        }
        try {
            webDriver.switchTo().frame(targetTab-1);
            return true;
        }
        catch (Exception exception){

        }
        return false;
    }
}
