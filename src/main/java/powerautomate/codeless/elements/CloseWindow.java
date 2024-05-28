package powerautomate.codeless.elements;

import org.openqa.selenium.WebDriver;

public class CloseWindow implements Element{
    @Override
    public boolean execute(WebDriver webDriver) {
        webDriver.close();
        return false;
    }
}
