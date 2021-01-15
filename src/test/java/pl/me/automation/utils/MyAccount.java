package pl.me.automation.utils;

import java.util.Properties;

public class MyAccount {

    private final String myAccountPageEnterRegisterUser;
    private final String myAccountPageEnterRegisterUserEmail;
    private final String myAccountPageEnterRegisterUserPassword;
    private final String myAccountPageEnterEmptyField;
    private final String myAccountPageGetRegistrationAndLoginErrorEmailText;
    private final String myAccountPageGetRegistrationAndLoginUserErrorText;
    private final String myAccountPageGetRegistrationAndLoginErrorPasswordText;
    private final String myAccountPageGetUserNameText;
    private final String myAccountPageGetRegistrationAndLoginErrorText;
    private final String myAccountPageEnterLoginUserLoginIncorrectPassword;
    private final String myAccountPageEnterIncorrectLoginUserNameOrEmail;
    private final String myAccountPageGetRegistrationAndLoginRequiredUserErrorText;
    private final String changePasswordPageEnterAccountEmail;
    private final String[] myAccountPageGetErrorLabels;
    private final String editShippingAddressGetShippingAddressFormAlert;
    private final String myAccountPageEnterLoginUserNameOrEmail1;
    private final String myAccountPageEnterLoginUserLoginPassword1;
    private final String myAccountPageFillLoginAsUserFormEnterEmail;
    private final String myAccountPageFillLoginAsUserFormEnterPassword;
    private final String myAccountPageRememberMeCheckboxEnterEmail;
    private final String myAccountPageRememberMeCheckboxEnterPassword;
    private final String editAddressPageEnterBillingUserLastName;
    private final String editAddressPageEnterBillingUserName;
    private final String editAddressPageSelectBillingCountryName;
    private final String editAddressPageEnterBillingAddress1;
    private final String editAddressPageEnterBillingPostCode;
    private final String editAddressPageEnterBillingCity;
    private final String editAddressPageEnterBillingRegion;
    private final String editAddressPageEnterBillingPhoneNumber;
    private final String editAddressPageEnterIncorrectBillingPhoneNumber;
    private final String editShippingAddressEnterShippingPostalCode;
    private final String[] myAccountPageGetShippingErrorLabels;
    private final String[] myAccountPageGetBillingErrorLabels;



    public MyAccount(Properties properties) {
        myAccountPageEnterRegisterUser = properties.getProperty("myAccountPage.enterRegisterUser");
        myAccountPageEnterRegisterUserEmail = properties.getProperty("myAccountPage.enterRegisterUserEmail");
        myAccountPageEnterRegisterUserPassword = properties.getProperty("myAccountPage.enterRegisterUserPassword");
        myAccountPageEnterEmptyField = properties.getProperty("myAccountPage.enterEmptyField");
        myAccountPageGetRegistrationAndLoginErrorEmailText = properties.getProperty("myAccountPage.getRegistrationAndLoginErrorEmailText");
        myAccountPageGetRegistrationAndLoginUserErrorText = properties.getProperty("myAccountPage.getRegistrationAndLoginUserErrorText");
        myAccountPageGetRegistrationAndLoginErrorPasswordText = properties.getProperty("myAccountPage.getRegistrationAndLoginErrorPasswordText");
        myAccountPageGetUserNameText = properties.getProperty("myAccountPage.getUserNameText");
        myAccountPageGetRegistrationAndLoginErrorText = properties.getProperty("myAccountPage.getRegistrationAndLoginErrorText");
        myAccountPageEnterLoginUserLoginIncorrectPassword = properties.getProperty("myAccountPage.enterLoginUserLoginIncorrectPassword");
        myAccountPageEnterIncorrectLoginUserNameOrEmail = properties.getProperty("myAccountPage.enterIncorrectLoginUserNameOrEmail");
        myAccountPageGetRegistrationAndLoginRequiredUserErrorText = properties.getProperty("myAccountPage.getRegistrationAndLoginRequiredUserErrorText");
        changePasswordPageEnterAccountEmail = properties.getProperty("changePasswordPage.enterAccountEmail");
        myAccountPageGetErrorLabels = properties.getProperty("myAccountPage.getErrorLabels").split(",");
        editShippingAddressGetShippingAddressFormAlert = properties.getProperty("editShippingAddress.getShippingAddressFormAlert");
        editAddressPageEnterBillingUserName = properties.getProperty("editAddressPage.enterBillingUserName");
        editAddressPageEnterBillingUserLastName = properties.getProperty("editAddressPage.enterBillingUserLastName");
        editAddressPageSelectBillingCountryName = properties.getProperty("editAddressPage.selectBillingCountryName");
        editAddressPageEnterBillingAddress1 = properties.getProperty("editAddressPage.enterBillingAddress1");
        editAddressPageEnterBillingPostCode = properties.getProperty("editAddressPage.enterBillingPostCode");
        editAddressPageEnterBillingCity = properties.getProperty("editAddressPage.enterBillingCity");
        editAddressPageEnterBillingRegion = properties.getProperty("editAddressPage.enterBillingRegion");
        editAddressPageEnterBillingPhoneNumber = properties.getProperty("editAddressPage.enterBillingPhoneNumber");
        editAddressPageEnterIncorrectBillingPhoneNumber = properties.getProperty("editAddressPage.enterIncorrectBillingPhoneNumber");
        editShippingAddressEnterShippingPostalCode = properties.getProperty("editShippingAddress.enterShippingPostalCode");
        myAccountPageEnterLoginUserNameOrEmail1 = properties.getProperty("myAccountPage.enterLoginUserNameOrEmail1");
        myAccountPageEnterLoginUserLoginPassword1 = properties.getProperty("myAccountPage.enterLoginUserLoginPassword1");
        myAccountPageFillLoginAsUserFormEnterEmail = properties.getProperty("myAccountPage.FillLoginAsUserFormEnterEmail");
        myAccountPageFillLoginAsUserFormEnterPassword = properties.getProperty("myAccountPage.FillLoginAsUserFormEnterPassword");
        myAccountPageRememberMeCheckboxEnterEmail = properties.getProperty("myAccountPage.RememberMeCheckboxEnterEmail");
        myAccountPageRememberMeCheckboxEnterPassword = properties.getProperty("myAccountPage.RememberMeCheckboxEnterPassword");
        myAccountPageGetShippingErrorLabels = properties.getProperty("myAccountPage.getShippingErrorLabels").split(",");;
        myAccountPageGetBillingErrorLabels = properties.getProperty("myAccountPage.getBillingErrorLabels").split(",");;


    }

    public String getMyAccountPageEnterRegisterUser() {
        return myAccountPageEnterRegisterUser;
    }

    public String getMyAccountPageEnterRegisterUserEmail() {
        return myAccountPageEnterRegisterUserEmail;
    }

    public String getMyAccountPageEnterRegisterUserPassword() {
        return myAccountPageEnterRegisterUserPassword;
    }
    public String getMyAccountPageEnterEmptyField() {
        return myAccountPageEnterEmptyField;
    }

    public String getMyAccountPageGetRegistrationAndLoginErrorEmailText() {
        return myAccountPageGetRegistrationAndLoginErrorEmailText;
    }

    public String getMyAccountPageGetRegistrationAndLoginUserErrorText() {
        return myAccountPageGetRegistrationAndLoginUserErrorText;
    }

    public String getMyAccountPageGetRegistrationAndLoginErrorPasswordText() {
        return myAccountPageGetRegistrationAndLoginErrorPasswordText;
    }

    public String getMyAccountPageGetUserNameText() {
        return myAccountPageGetUserNameText;
    }

    public String getMyAccountPageGetRegistrationAndLoginErrorText() {
        return myAccountPageGetRegistrationAndLoginErrorText;
    }

    public String getMyAccountPageEnterLoginUserLoginIncorrectPassword() {
        return myAccountPageEnterLoginUserLoginIncorrectPassword;
    }

    public String getMyAccountPageEnterIncorrectLoginUserNameOrEmail() {
        return myAccountPageEnterIncorrectLoginUserNameOrEmail;
    }

    public String getMyAccountPageGetRegistrationAndLoginRequiredUserErrorText() {
        return myAccountPageGetRegistrationAndLoginRequiredUserErrorText;
    }


    public String getChangePasswordPageEnterAccountEmail() {
        return changePasswordPageEnterAccountEmail;
    }


    public String[] getMyAccountPageGetErrorLabels() {
        return myAccountPageGetErrorLabels;
    }


    public String getEditShippingAddressGetShippingAddressFormAlert() {
        return editShippingAddressGetShippingAddressFormAlert;
    }

    public String getMyAccountPageEnterLoginUserNameOrEmail1() {
        return myAccountPageEnterLoginUserNameOrEmail1;
    }

    public String getMyAccountPageEnterLoginUserLoginPassword1() {
        return myAccountPageEnterLoginUserLoginPassword1;
    }

    public String getMyAccountPageFillLoginAsUserFormEnterEmail() {
        return myAccountPageFillLoginAsUserFormEnterEmail;
    }

    public String getMyAccountPageFillLoginAsUserFormEnterPassword() {
        return myAccountPageFillLoginAsUserFormEnterPassword;
    }

    public String getMyAccountPageRememberMeCheckboxEnterEmail() {
        return myAccountPageRememberMeCheckboxEnterEmail;
    }

    public String getMyAccountPageRememberMeCheckboxEnterPassword() {
        return myAccountPageRememberMeCheckboxEnterPassword;
    }


    public String getEditAddressPageEnterBillingUserLastName() {
        return editAddressPageEnterBillingUserLastName;
    }

    public String getEditAddressPageEnterBillingUserName() {
        return editAddressPageEnterBillingUserName;
    }

    public String getEditAddressPageSelectBillingCountryName() {
        return editAddressPageSelectBillingCountryName;
    }

    public String getEditAddressPageEnterBillingAddress1() {
        return editAddressPageEnterBillingAddress1;
    }

    public String getEditAddressPageEnterBillingPostCode() {
        return editAddressPageEnterBillingPostCode;
    }

    public String getEditAddressPageEnterBillingCity() {
        return editAddressPageEnterBillingCity;
    }

    public String getEditAddressPageEnterBillingRegion() {
        return editAddressPageEnterBillingRegion;
    }

    public String getEditAddressPageEnterBillingPhoneNumber() {
        return editAddressPageEnterBillingPhoneNumber;
    }

    public String getEditAddressPageEnterIncorrectBillingPhoneNumber() {
        return editAddressPageEnterIncorrectBillingPhoneNumber;
    }

    public String getEditShippingAddressEnterShippingPostalCode() {
        return editShippingAddressEnterShippingPostalCode;
    }


    public String[] getMyAccountPageGetShippingErrorLabels() {
        return myAccountPageGetShippingErrorLabels;
    }

    public String[] getMyAccountPageGetBillingErrorLabels() {
        return myAccountPageGetBillingErrorLabels;
    }

}
