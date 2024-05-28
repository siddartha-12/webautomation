package powerautomate.codeless.providers.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverFactory implements Factory<WebDriver> {
    @Override
    public WebDriver getInstance() {
        return new FirefoxDriver();
    }
}
