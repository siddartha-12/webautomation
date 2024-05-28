package powerautomate.codeless.providers.drivers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory implements Factory<WebDriver> {

    @Override
    public WebDriver getInstance() {
        return new ChromeDriver();
    }
}
