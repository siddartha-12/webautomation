package powerautomate.codeless.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import powerautomate.codeless.selectors.Selector;

@AllArgsConstructor
@Getter
@Setter
public class TextValidator implements Element{
    private Selector selectorAbstract;
    @Override
    public boolean execute(WebDriver webDriver) {

        try {
            WebElement webElement = selectorAbstract.getElement(webDriver);
            if(webElement!=null){
                return webElement.isDisplayed();
            }
        }
        catch (Exception exception){

        }

        return false;
    }
}
