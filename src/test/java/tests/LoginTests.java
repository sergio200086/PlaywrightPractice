package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

public class LoginTests {

    LoginPage loginPage;


    @BeforeEach
    public void setUp(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setChannel("chrome")
        );
        Page page = browser.newPage();
        this.loginPage = new LoginPage(page);
        this.loginPage.navigate("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void testLoginFeature(){
        this.loginPage.enterUsername("tomsmith");
        this.loginPage.enterPassword("SuperSecretPassword!");
        this.loginPage.clickOnLoginButton();
        String message = this.loginPage.getResultMessage();
        Assertions.assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    public void testWithIncorrectUsername(){
        this.loginPage.enterUsername("pepitoperez");
        this.loginPage.enterPassword("SuperSecretPassword!");
        this.loginPage.clickOnLoginButton();
        String message = this.loginPage.getResultMessage();
        Assertions.assertTrue(message.contains("Your username is invalid!"));
    }

    @Test
    public void testWithIncorrectPassword(){
        this.loginPage.enterUsername("tomsmith");
        this.loginPage.enterPassword("ContraMala");
        this.loginPage.clickOnLoginButton();
        String message = this.loginPage.getResultMessage();
        Assertions.assertTrue(message.contains("Your password is invalid!"));
    }

    @AfterEach
    public void tearDown(){
        if(this.loginPage != null)
            this.loginPage.closePage();
    }
}
