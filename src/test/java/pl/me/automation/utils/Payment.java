package pl.me.automation.utils;

import java.util.Properties;

public class Payment {

    private final String[] labels;

    private final String billingCity;
    private final String shippingMethodRate;
    private final String paymentPageApplyCouponCode;
    private final String paymentPageChoosePaymentMethod;
    private final String paymentPageEnterBillingUserLastName;
    private final String paymentPageEnterBillingUserName;
    private final String paymentPageEnterBillingUserAddress;
    private final String paymentPageEnterBillingUserPostCode;
    private final String paymentPageEnterBillingUserPhone;
    private final String paymentPageEnterBillingUserEmail;
    private final String paymentPageEnterBillingAccountUsername;
    private final String paymentPageEnterBillingAccountPassword;
    private final String paymentPageSelectBillingCountry;
    private final String paymentPageSelectBillingCountryOtherThanPL;
    private final String paymentPageGetFormValidationErrorText;
    private final String paymentPageEnterUserNameOrEmail;
    private final String paymentPageEnterUserPassword;
    private final String paymentPageMyAccountUserText;
    private final String paymentPageEnterUserIncorrectPassword;
    private final String paymentPageGetErrorText;
    private final String myAccountPageGetResetPasswordSuccessSendAlert;
    private final String myAccountPageEnterLoginOrEmailToLostPasswordUserField;
    private final String paymentPageGetBillingNameErrorText;
    private final String paymentPageChoosePaymentMethodStripe;
    private final String paymentPageCorrectCardDetailsCardNumber;
    private final String paymentPageCorrectCardDetailsCardDate;
    private final String paymentPageCorrectCardDetailsCardCVC;
    private final String paymentPageGetUnableToProcessOrderAlertText;
    private final String paymentPageExpiredCardDetailsCardNumber;
    private final String paymentPageExpiredCardDetailsCardDate;
    private final String paymentPageGetStripeCardValidityErrorText;
    private final String paymentPageIncompleteCardDetailsCardNumber;
    private final String paymentPageIncompleteCardDetailsCardDate;
    private final String paymentPageIncompleteCardDetailsCardCVC;
    private final String paymentPageGetStripeCardValidityErrorTextCVCIncomplete;
    private final String paymentPageIncorrectCardDetailsCardNumber2;
    private final String paymentPageIncorrectCardDetailsCardDate2;
    private final String paymentPageIncorrectCardDetailsCardCVC2;
    private final String paymentPageGetInvalidStripeCardValidityErrorText;
    private final String paymentPageExpiredCard2DetailsCardNumber;
    private final String paymentPageGetUnableToProcessOrderAlertTextExpiredCard;
    private final String paymentPageEnterBillingUserEmailDomain;
    private final String paymentPageEnterProvince;
    private final String paymentPageEnterIncorrectUserPostCode;


    public Payment(Properties properties) {
        labels = properties.getProperty("paymentPage.getErrorLabels").split(",");
        billingCity = properties.getProperty("paymentPage.enterBillingUserCity");
        shippingMethodRate = properties.getProperty("paymentPage.getShippingMethodRate");
        paymentPageApplyCouponCode = properties.getProperty("paymentPage.applyCouponCode");
        paymentPageChoosePaymentMethod = properties.getProperty("paymentPage.choosePaymentMethodPayu");
        paymentPageEnterBillingUserName= properties.getProperty("paymentPage.enterUserName");
        paymentPageEnterBillingUserLastName= properties.getProperty("paymentPage.enterBillingUserLastName");
        paymentPageEnterBillingUserAddress= properties.getProperty("paymentPage.enterBillingUserAddress");
        paymentPageEnterBillingUserPostCode= properties.getProperty("paymentPage.enterBillingUserPostCode");
        paymentPageEnterBillingUserPhone= properties.getProperty("paymentPage.enterBillingUserPhone");
        paymentPageEnterBillingUserEmail= properties.getProperty("paymentPage.enterBillingUserEmail");
        paymentPageEnterBillingAccountUsername= properties.getProperty("paymentPage.enterBillingAccountUsername");
        paymentPageEnterBillingAccountPassword= properties.getProperty("paymentPage.enterBillingAccountPassword");
        paymentPageSelectBillingCountry = properties.getProperty("paymentPage.selectBillingCountry");
        paymentPageSelectBillingCountryOtherThanPL = properties.getProperty("paymentPage.selectBillingCountryOtherThanPL");
        paymentPageGetFormValidationErrorText = properties.getProperty("paymentPage.getFormValidationErrorText");
        paymentPageEnterUserNameOrEmail= properties.getProperty("paymentPage.enterUserNameOrEmail");
        paymentPageEnterUserPassword = properties.getProperty("paymentPage.enterUserPassword");
        paymentPageMyAccountUserText = properties.getProperty("paymentPage.myAccountUserText");
        paymentPageEnterUserIncorrectPassword = properties.getProperty("paymentPage.enterUserIncorrectPassword");
        paymentPageGetErrorText = properties.getProperty("paymentPage.getErrorText");
        myAccountPageGetResetPasswordSuccessSendAlert = properties.getProperty("myAccountPage.getResetPasswordSuccessSendAlert");
        myAccountPageEnterLoginOrEmailToLostPasswordUserField = properties.getProperty("myAccountPage.enterLoginOrEmailToLostPasswordUserField");
        paymentPageGetBillingNameErrorText = properties.getProperty("paymentPage.getBillingNameErrorText");
        paymentPageChoosePaymentMethodStripe = properties.getProperty("paymentPage.choosePaymentMethodStripe");
        paymentPageCorrectCardDetailsCardNumber = properties.getProperty("paymentPage.CorrectCardDetailsCardNumber");
        paymentPageCorrectCardDetailsCardDate = properties.getProperty("paymentPage.CorrectCardDetailsCardDate");
        paymentPageCorrectCardDetailsCardCVC = properties.getProperty("paymentPage.CorrectCardDetailsCardCVC");
        paymentPageGetUnableToProcessOrderAlertText= properties.getProperty("paymentPage.getUnableToProcessOrderAlertText");
        paymentPageExpiredCardDetailsCardNumber= properties.getProperty("paymentPage.IncorrectCardDetailsCardNumber");
        paymentPageExpiredCardDetailsCardDate= properties.getProperty("paymentPage.ExpiredCardDetailsCardDate");
        paymentPageGetStripeCardValidityErrorText= properties.getProperty("paymentPage.getStripeCardValidityErrorTextIncorrectDate");
        paymentPageIncompleteCardDetailsCardNumber= properties.getProperty("paymentPage.IncompleteCardDetailsCardNumber");
        paymentPageIncompleteCardDetailsCardDate= properties.getProperty("paymentPage.IncompleteCardDetailsCardDate");
        paymentPageIncompleteCardDetailsCardCVC= properties.getProperty("paymentPage.IncompleteCardDetailsCardCVC");
        paymentPageGetStripeCardValidityErrorTextCVCIncomplete= properties.getProperty("paymentPage.getStripeCardValidityErrorTextCVCIncomplete");
        paymentPageIncorrectCardDetailsCardNumber2= properties.getProperty("paymentPage.IncorrectCardDetailsCardNumber2");
        paymentPageIncorrectCardDetailsCardDate2= properties.getProperty("paymentPage.IncorrectCardDetailsCardDate2");
        paymentPageIncorrectCardDetailsCardCVC2= properties.getProperty("paymentPage.IncorrectCardDetailsCardCVC2");
        paymentPageGetInvalidStripeCardValidityErrorText= properties.getProperty("paymentPage.getInvalidStripeCardValidityErrorText");
        paymentPageExpiredCard2DetailsCardNumber= properties.getProperty("paymentPage.ExpiredCard2DetailsCardNumber");
        paymentPageGetUnableToProcessOrderAlertTextExpiredCard  = properties.getProperty("paymentPage.getUnableToProcessOrderAlertTextExpiredCard");
        paymentPageEnterBillingUserEmailDomain = properties.getProperty("paymentPage.enterBillingUserEmailDomain");
        paymentPageEnterProvince = properties.getProperty("paymentPage.enterProvince");
        paymentPageEnterIncorrectUserPostCode = properties.getProperty("paymentPage.enterIncorrectUserPostCode");
    }

    public String[] getLabels() {
        return labels;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getShippingMethodRate() {
        return shippingMethodRate;
    }

    public String getPaymentPageApplyCouponCode() {
        return paymentPageApplyCouponCode;
    }

    public String getPaymentPageChoosePaymentMethod() {
        return paymentPageChoosePaymentMethod;
    }
    public String getPaymentPageEnterBillingUserLastName() {
        return paymentPageEnterBillingUserLastName;
    }

    public String getPaymentPageEnterBillingUserName() {
        return paymentPageEnterBillingUserName;
    }

    public String getPaymentPageEnterBillingUserAddress() {
        return paymentPageEnterBillingUserAddress;
    }

    public String getPaymentPageEnterBillingUserPostCode() {
        return paymentPageEnterBillingUserPostCode;
    }

    public String getPaymentPageEnterBillingUserPhone() {
        return paymentPageEnterBillingUserPhone;
    }

    public String getPaymentPageEnterBillingUserEmail() {
        return paymentPageEnterBillingUserEmail;
    }

    public String getPaymentPageEnterBillingAccountUsername() {
        return paymentPageEnterBillingAccountUsername;
    }

    public String getPaymentPageEnterBillingAccountPassword() {
        return paymentPageEnterBillingAccountPassword;
    }


    public Integer getPaymentPageSelectBillingCountry() {
        return Integer.valueOf(paymentPageSelectBillingCountry);
    }

    public Integer getPaymentPageSelectBillingCountryOtherThanPL() {
        return Integer.valueOf(paymentPageSelectBillingCountryOtherThanPL);
    }

    public String getPaymentPageGetFormValidationErrorText() {
        return paymentPageGetFormValidationErrorText;
    }


    public String getPaymentPageEnterUserNameOrEmail() {
        return paymentPageEnterUserNameOrEmail;
    }

    public String getPaymentPageEnterUserPassword() {
        return paymentPageEnterUserPassword;
    }


    public String getPaymentPageMyAccountUserText() {
        return paymentPageMyAccountUserText;
    }

    public String getPaymentPageEnterUserIncorrectPassword() {
        return paymentPageEnterUserIncorrectPassword;
    }

    public String getPaymentPageGetErrorText() {
        return paymentPageGetErrorText;
    }

    public String getMyAccountPageGetResetPasswordSuccessSendAlert() {
        return myAccountPageGetResetPasswordSuccessSendAlert;
    }

    public String getMyAccountPageEnterLoginOrEmailToLostPasswordUserField() {
        return myAccountPageEnterLoginOrEmailToLostPasswordUserField;
    }


    public String getPaymentPageGetBillingNameErrorText() {
        return paymentPageGetBillingNameErrorText;
    }


    public String getPaymentPageChoosePaymentMethodStripe() {
        return paymentPageChoosePaymentMethodStripe;
    }

    public String getPaymentPageCorrectCardDetailsCardNumber() {
        return paymentPageCorrectCardDetailsCardNumber;
    }

    public String getPaymentPageCorrectCardDetailsCardDate() {
        return paymentPageCorrectCardDetailsCardDate;
    }

    public String getPaymentPageCorrectCardDetailsCardCVC() {
        return paymentPageCorrectCardDetailsCardCVC;
    }

    public String getPaymentPageGetUnableToProcessOrderAlertText() {
        return paymentPageGetUnableToProcessOrderAlertText;
    }

    public String getPaymentPageExpiredCardDetailsCardNumber() {
        return paymentPageExpiredCardDetailsCardNumber;
    }

    public String getPaymentPageExpiredCardDetailsCardDate() {
        return paymentPageExpiredCardDetailsCardDate;
    }

    public String getPaymentPageGetStripeCardValidityErrorText() {
        return paymentPageGetStripeCardValidityErrorText;
    }


    public String getPaymentPageIncompleteCardDetailsCardNumber() {
        return paymentPageIncompleteCardDetailsCardNumber;
    }

    public String getPaymentPageIncompleteCardDetailsCardDate() {
        return paymentPageIncompleteCardDetailsCardDate;
    }

    public String getPaymentPageIncompleteCardDetailsCardCVC() {
        return paymentPageIncompleteCardDetailsCardCVC;
    }

    public String getPaymentPageGetStripeCardValidityErrorTextCVCIncomplete() {
        return paymentPageGetStripeCardValidityErrorTextCVCIncomplete;
    }


    public String getPaymentPageIncorrectCardDetailsCardNumber2() {
        return paymentPageIncorrectCardDetailsCardNumber2;
    }

    public String getPaymentPageIncorrectCardDetailsCardDate2() {
        return paymentPageIncorrectCardDetailsCardDate2;
    }

    public String getPaymentPageIncorrectCardDetailsCardCVC2() {
        return paymentPageIncorrectCardDetailsCardCVC2;
    }

    public String getPaymentPageGetInvalidStripeCardValidityErrorText() {
        return paymentPageGetInvalidStripeCardValidityErrorText;
    }

    public String getPaymentPageExpiredCard2DetailsCardNumber() {
        return paymentPageExpiredCard2DetailsCardNumber;
    }

    public String getPaymentPageGetUnableToProcessOrderAlertTextExpiredCard() {
        return paymentPageGetUnableToProcessOrderAlertTextExpiredCard;
    }

    public String getPaymentPageEnterBillingUserEmailDomain() {
        return paymentPageEnterBillingUserEmailDomain;
    }

    public String getPaymentPageEnterProvince() {
        return paymentPageEnterProvince;
    }

    public String getPaymentPageEnterIncorrectUserPostCode() {
        return paymentPageEnterIncorrectUserPostCode;
    }



}
