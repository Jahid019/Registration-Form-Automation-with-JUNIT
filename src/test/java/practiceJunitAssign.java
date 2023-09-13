import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class practiceJunitAssign {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js =(JavascriptExecutor) driver;
    @BeforeAll

    public void setup(){
        driver= new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }
    @DisplayName("Visit the website and display its title")
    @Test
    public void displayTitle(){
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String actual_title= driver.getTitle();
        String expected_title = "Practice webform for learners";
        Assertions.assertTrue(actual_title.contains(expected_title));
        System.out.println(actual_title);

    }

    @DisplayName("Show logo of the website")
    @Test
    public void showLogo(){
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        boolean isExist = driver.findElement(By.cssSelector("[class='site-logo d-block']")).isDisplayed();
        assertTrue(isExist);

    }
    @DisplayName("Hover in any menu and it will display its features")
    @Test
    public void mouseHover() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        WebElement menuWrite = driver.findElement(By.xpath("//li[@id='main-menu-link-content30997177-7e53-4625-8df2-0d2eb49bd8fa']//span[@class='sf-depth-1 menuparent nolink sf-with-ul']"));
        Actions actions= new Actions(driver);
        actions.moveToElement(menuWrite).perform();
        Thread.sleep(3000);
    }
    @DisplayName("User should submit the form properly")
    @Test
    public void submitForm()  {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        //name
        driver.findElement(By.id("edit-name")).click();
        driver.findElement(By.id("edit-name")).sendKeys("Jahid hasan");
        driver.findElement(By.id("edit-number")).click();
        driver.findElement(By.id("edit-number")).sendKeys("01706262821");
        driver.findElement(By.xpath("//label[normalize-space()='30-40']")).click();

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();

        String currentDate = dateFormat.format(date);
        driver.findElement(By.id("edit-date")).sendKeys(currentDate);
        driver.findElement(By.id("edit-email")).sendKeys("jahidhasan@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Im a very good boy");

        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        uploadElement.sendKeys("C:\\Users\\USER\\Pictures\\1nos.PNG");
    }

    @Test

    public void confirmCheckBox(){
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement elementToScroll = driver.findElement(By.id("edit-age"));

        jsExecutor.executeScript("arguments[0].scrollIntoView();", elementToScroll);
    }

    @Test
    public void SubmitButton(){
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        WebElement element = driver.findElement(By.id("edit-submit"));
        jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }

    @Test
    public void assertMessage(){
        driver.get("https://www.digitalunite.com/node/5932/webform/confirmation?token=XOxF4_IPzu9Teyhv7LpcJ2Qoh082shU62-6ZYoNfaV0");
        String message= "Thank you for your submission!";
        String expected_message = driver.findElement(By.xpath("//h1[normalize-space()='Thank you for your submission!']")).getText();
        Assertions.assertTrue(expected_message.contains(message));
    }

    @AfterAll
    public void closeDriver(){
        driver.quit();
    }

}
