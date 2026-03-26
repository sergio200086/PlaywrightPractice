package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    //Selectors
    private final String userName = "//input[@id='username']";
    private final String password = "//input[@id='password']";
    private final String loginButton = "//*[@id='login']/button";
    private final String succesfulLogin = "//div[@id='flash']";

    private final Page page;
    public LoginPage(Page page){
        this.page = page;
    }

    public void navigate(String url){
        this.page.navigate(url);
    }

    public void enterUsername(String name){
        this.page.fill(this.userName, name);
    }

    public void enterPassword(String password){
        this.page.fill(this.password, password);
    }

    public void clickOnLoginButton(){
        this.page.click(this.loginButton);
    }

    public String getResultMessage(){
        return this.page.textContent(this.succesfulLogin);
    }

    public void closePage(){
        this.page.close();
    }
}
