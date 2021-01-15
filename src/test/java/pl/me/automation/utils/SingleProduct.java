package pl.me.automation.utils;

import java.util.Properties;

public class SingleProduct {


    private final String singleProductPageClickRecommendedProductsButton1;
    private final String singleProductPageClickRecommendedProductsButton0;
    private final String singleProductPageAddStarRating;
    private final String singleProductPageEnterComment;
    private final String singleProductPageEnterUserName;
    private final String singleProductPageEnterUserEmail;
    private final String singleProductPageGetAlertBoxText;
    private final String singleProductPageGetNoReviewsText;
    private final String singleProductPageGetAddedStarRating;
    private final String singleProductPageEnterReplacement;
    private final String singleProductPageEnterReplacement2;



    public SingleProduct(Properties properties){
        singleProductPageClickRecommendedProductsButton0 = properties.getProperty("singleProductPage.clickRecommendedProductsButton0");
        singleProductPageClickRecommendedProductsButton1 = properties.getProperty("singleProductPage.clickRecommendedProductsButton1");
        singleProductPageAddStarRating = properties.getProperty("singleProductPage.addStarRating");
        singleProductPageEnterComment = properties.getProperty("singleProductPage.enterComment");
        singleProductPageEnterUserName = properties.getProperty("singleProductPage.enterUserName");
        singleProductPageEnterUserEmail = properties.getProperty("singleProductPage.enterUserEmail");
        singleProductPageGetAlertBoxText = properties.getProperty("singleProductPage.getAlertBoxText");
        singleProductPageGetNoReviewsText = properties.getProperty("singleProductPage.getNoReviewsText");
        singleProductPageGetAddedStarRating = properties.getProperty("singleProductPage.getAddedStarRating");
        singleProductPageEnterReplacement = properties.getProperty("singleProductPage.enterReplacement1");
        singleProductPageEnterReplacement2 = properties.getProperty("singleProductPage.enterReplacement2");

    }

    public String getSingleProductPageClickRecommendedProductsButton1() {
        return singleProductPageClickRecommendedProductsButton1;
    }

    public String getSingleProductPageClickRecommendedProductsButton0() {
        return singleProductPageClickRecommendedProductsButton0;
    }

    public String getSingleProductPageAddStarRating() {
        return singleProductPageAddStarRating;
    }

    public String getSingleProductPageEnterComment() {
        return singleProductPageEnterComment;
    }


    public String getSingleProductPageEnterUserName() {
        return singleProductPageEnterUserName;
    }

    public String getSingleProductPageEnterUserEmail() {
        return singleProductPageEnterUserEmail;
    }

    public String getSingleProductPageGetAlertBoxText() {
        return singleProductPageGetAlertBoxText;
    }

    public String getSingleProductPageGetNoReviewsText() {
        return singleProductPageGetNoReviewsText;
    }

    public String getSingleProductPageGetAddedStarRating() {
        return singleProductPageGetAddedStarRating;
    }

    public String getSingleProductPageEnterReplacement() {
        return singleProductPageEnterReplacement;
    }

    public String getSingleProductPageEnterReplacement2() {
        return singleProductPageEnterReplacement2;
    }

}
