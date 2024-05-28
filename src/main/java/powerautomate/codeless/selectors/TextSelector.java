package powerautomate.codeless.selectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextSelector extends Selector {
    TextSelector(String value) {
        super(value);
    }

    @Override
    public WebElement getElement(WebDriver webDriver) {
        try {
            WebElement webElement = webDriver.findElement(By.partialLinkText(getValue()));
            return webElement;
        }
        catch (Exception exception){
            return null;
        }
    }
}
