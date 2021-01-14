package pl.me.automation.utils;

import java.util.Properties;

public class WishList {

    private final String wishListPageProduct;

    private final String wistListPageAddProductFromWishListToCard;

    public WishList(Properties properties) {
        wishListPageProduct = properties.getProperty("wishListPage.product");
        wistListPageAddProductFromWishListToCard = properties.getProperty("wistListPage.addProductFromWishListToCard");

    }


    public String getWishListPageProduct() {
        return wishListPageProduct;
    }


    public String getWistListPageAddProductFromWishListToCard() {
        return wistListPageAddProductFromWishListToCard;
    }




}
