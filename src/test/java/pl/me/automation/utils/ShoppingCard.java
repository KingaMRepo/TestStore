package pl.me.automation.utils;

import java.util.Properties;

public class ShoppingCard {


    private final String shoppingCardPageGetDeliveryType;
    private final String shoppingCardPageGetSummaryPrice;
    private final String shoppingCardPageApplyCouponCode;
    private final String shoppingCardPageGetAppliedCouponFailAlertMessageOne;
    private final String shoppingCardPageGetAppliedCouponFailAlertMessageTwo;
    private final String shoppingCardPageChooseShippingMethod;
    private final String shoppingCardPageApplyCorrectCouponCode;
    private final String shoppingCardPageGetAppliedCouponSuccessfullyAlertMessage;
    private final String shoppingCardPageReplaceTarget;
    private final String shoppingCardPageZero;
    private final String shoppingCardPageReplacement;



    public ShoppingCard(Properties properties){
        shoppingCardPageGetDeliveryType = properties.getProperty("shoppingCardPage.getDeliveryType");
        shoppingCardPageGetSummaryPrice = properties.getProperty("shoppingCardPage.getSummaryPrice");
        shoppingCardPageApplyCouponCode = properties.getProperty("shoppingCardPage.applyCouponCode");
        shoppingCardPageGetAppliedCouponFailAlertMessageOne = properties.getProperty("shoppingCardPage.getAppliedCouponFailAlertMessageOne");
        shoppingCardPageGetAppliedCouponFailAlertMessageTwo = properties.getProperty("shoppingCardPage.getAppliedCouponFailAlertMessageTwo");
        shoppingCardPageChooseShippingMethod = properties.getProperty("shoppingCardPage.chooseShippingMethod");
        shoppingCardPageApplyCorrectCouponCode = properties.getProperty("shoppingCardPage.applyCorrectCouponCode");
        shoppingCardPageGetAppliedCouponSuccessfullyAlertMessage = properties.getProperty("shoppingCardPage.getAppliedCouponSuccessfullyAlertMessage");
        shoppingCardPageReplaceTarget = properties.getProperty("shoppingCardPage.replaceTarget");
        shoppingCardPageReplacement = properties.getProperty("shoppingCardPage.replacement");
        shoppingCardPageZero = properties.getProperty("shoppingCardPage.zero");

    }


    public String getShoppingCardPageGetDeliveryType() {
        return shoppingCardPageGetDeliveryType;
    }

    public String getShoppingCardPageGetSummaryPrice() {
        return shoppingCardPageGetSummaryPrice;
    }

    public String getShoppingCardPageApplyCouponCode() {
        return shoppingCardPageApplyCouponCode;
    }

    public String getShoppingCardPageGetAppliedCouponFailAlertMessageOne() {
        return shoppingCardPageGetAppliedCouponFailAlertMessageOne;
    }

    public String getShoppingCardPageGetAppliedCouponFailAlertMessageTwo() {
        return shoppingCardPageGetAppliedCouponFailAlertMessageTwo;
    }


    public String getShoppingCardPageChooseShippingMethod() {
        return shoppingCardPageChooseShippingMethod;
    }


    public String getShoppingCardPageApplyCorrectCouponCode() {
        return shoppingCardPageApplyCorrectCouponCode;
    }

    public String getShoppingCardPageGetAppliedCouponSuccessfullyAlertMessage() {
        return shoppingCardPageGetAppliedCouponSuccessfullyAlertMessage;
    }

    public String getShoppingCardPageReplaceTarget() {
        return shoppingCardPageReplaceTarget;
    }

    public String getShoppingCardPageZero() {
        return shoppingCardPageZero;
    }

    public String getShoppingCardPageReplacement() {
        return shoppingCardPageReplacement;
    }


}
