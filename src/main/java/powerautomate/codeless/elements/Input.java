package powerautomate.codeless.elements;

import lombok.AllArgsConstructor;;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import powerautomate.codeless.selectors.Selector;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Input implements Element{
    private List<Selector> selectors;
    private String value;
    @Override
    public boolean execute(WebDriver webDriver) {
        for(Selector selector: selectors){
            try {
                WebElement webElement = selector.getElement(webDriver);
                if(webElement!=null){
                    webElement.sendKeys(value);
                    return true;
                }
            }
            catch (Exception exception){

            }
        }
        return false;
    }
}
