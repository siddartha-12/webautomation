package powerautomate.codeless.selectors;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Selector {
    @Getter @Setter
    private String value;
    Selector(String value){
        this.value = value;
    }
    public abstract WebElement getElement(WebDriver webDriver);
}
