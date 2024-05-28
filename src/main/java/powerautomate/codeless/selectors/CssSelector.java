package powerautomate.codeless.selectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CssSelector extends Selector {
    public CssSelector(String value) {
        super(value);
    }

    @Override
    public WebElement getElement(WebDriver webDriver) {
        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(getValue()));
            return webElement;
        }
        catch (Exception exception){
            return null;
        }
    }
}
