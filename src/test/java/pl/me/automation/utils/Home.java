package pl.me.automation.utils;

import java.util.Properties;

public class Home {

    private final String contactPageEnterUserName;
    private final String contactPageEnterUserLastName;
    private final String contactPageSelectMessageSubject;
    private final String contactPageEnterEmailAddress;
    private final String contactPageEnterMessage;
    private final String homeSelectShopCategory1;
    private final String homeSelectShopCategory2;
    private final String homePageAddProductsToWishList;
    private final String homePageAddProductsToWishList1;
    private final String homePageAddRecommendedProductsToBasket1;
    private final String homePageAddRecommendedProductsToBasket2;
    private final String homePageSelectShopCategory3;


    public Home(Properties properties) {
        contactPageEnterUserName = properties.getProperty("contact.enterUserName");
        contactPageEnterUserLastName = properties.getProperty("contact.contactPageEnterUserLastName");
        contactPageSelectMessageSubject = properties.getProperty("contact.contactPageSelectMessageSubject");
        contactPageEnterEmailAddress = properties.getProperty("contact.contactPageEnterEmailAddress");
        contactPageEnterMessage = properties.getProperty("contact.contactPageEnterMessage");
        homeSelectShopCategory1 = properties.getProperty("homePage.selectShopCategory1");
        homeSelectShopCategory2 = properties.getProperty("homePage.selectShopCategory2");
        homePageSelectShopCategory3 = properties.getProperty("homePage.selectShopCategory3");
        homePageAddProductsToWishList= properties.getProperty("homePage.addProductsToWishList0");
        homePageAddProductsToWishList1= properties.getProperty("homePage.addProductsToWishList1");
        homePageAddRecommendedProductsToBasket1 = properties.getProperty("homePage.addRecommendedProductsToBasket1");
        homePageAddRecommendedProductsToBasket2 = properties.getProperty("homePage.addRecommendedProductsToBasket2");

    }

    public String getContactPageEnterUserName() {
        return contactPageEnterUserName;
    }

    public String getContactPageEnterUserLastName() {
        return contactPageEnterUserLastName;
    }

    public String getContactPageSelectMessageSubject() {
        return contactPageSelectMessageSubject;
    }

    public String getContactPageEnterEmailAddress() {
        return contactPageEnterEmailAddress;
    }

    public String getContactPageEnterMessage() {
        return contactPageEnterMessage;
    }

    public String getHomeSelectShopCategory1() {
        return homeSelectShopCategory1;
    }

    public String getHomeSelectShopCategory2() {
        return homeSelectShopCategory2;
    }

    public String getHomePageAddProductsToWishList() {
        return homePageAddProductsToWishList;
    }


    public String getHomePageAddProductsToWishList1() {
        return homePageAddProductsToWishList1;
    }


    public String getHomePageAddRecommendedProductsToBasket1() {
        return homePageAddRecommendedProductsToBasket1;
    }

    public String getHomePageAddRecommendedProductsToBasket2() {
        return homePageAddRecommendedProductsToBasket2;
    }


    public String getHomePageSelectShopCategory3() {
        return homePageSelectShopCategory3;
    }
}
