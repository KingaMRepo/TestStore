package pl.me.automation;

import pl.me.automation.page.MyAccountPage;
import pl.me.automation.page.PaymentPage;

public abstract class MyAccountLogInForm {

    public void fillLoginForm(MyAccountPage myAccountPage){
        myAccountPage.enterLoginUserNameOrEmail("user134@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("B{}h8~GS/(.KL5km");
        myAccountPage.clickLoginSubmit();
    }

    public void fillLoginFormWitRememberMeCheckbox(MyAccountPage myAccountPage){
        myAccountPage.enterLoginUserNameOrEmail("user134@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("B{}h8~GS/(.KL5km");
        myAccountPage.clickLoginRememberMeCheckbox();
        myAccountPage.clickLoginSubmit();
    }




}
