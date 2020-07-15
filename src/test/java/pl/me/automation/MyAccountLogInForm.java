package pl.me.automation;

import pl.me.automation.page.MyAccountPage;
import pl.me.automation.page.PaymentPage;

public abstract class MyAccountLogInForm {

    public void fillLoginForm(MyAccountPage myAccountPage){
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&Lgqb");
        myAccountPage.clickLoginSubmit();
    }

    public void fillLoginFormWitRememberMeCheckbox(MyAccountPage myAccountPage){
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&Lgqb");
        myAccountPage.clickLoginRememberMeCheckbox();
        myAccountPage.clickLoginSubmit();
    }


}
