package powerautomate.codeless.selectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XpathSelector extends Selector {

    public XpathSelector(String value) {
        super(value);
    }

    @Override
    public WebElement getElement(WebDriver webDriver) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(getValue()));
            return webElement;
        }
        catch (Exception exception){
            return null;
        }
    }
}
