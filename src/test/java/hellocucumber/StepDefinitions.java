package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class StepDefinitions {
    private final WebDriver driver = new EdgeDriver();
    @Dado("que eu faca o login site")
    public void que_eu_faca_o_login_site() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        WebElement loginCredentialsElement = driver.findElement(By.id("login_credentials"));
        String userName = loginCredentialsElement.getText().split("\n")[1];
        driver.findElement(By.id("user-name")).sendKeys(userName);
        WebElement passwordCredentialsElement = driver.findElement(By.className("login_password"));
        String password = passwordCredentialsElement.getText().split("\n")[1];
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Quando("add um produto ao carrinho")
    public void add_um_produto_ao_carrinho() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
    }
    @Entao("clico em checkout para finalizar a compra")
    public void clico_em_checkout_para_finalizar_a_compra() {
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Marcelo");
        driver.findElement(By.id("last-name")).sendKeys("Torreao");
        driver.findElement(By.id("postal-code")).sendKeys("03712907");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
        WebElement comboBoxElement = driver.findElement(By.className("product_sort_container"));
        Select comboBox = new Select(comboBoxElement);
        comboBox.selectByValue("lohi");
    }
    @After
    public void doSomethingAfter(Scenario scenario){
        // Do something after after scenario
        if(scenario.isFailed()==false) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
    }
}