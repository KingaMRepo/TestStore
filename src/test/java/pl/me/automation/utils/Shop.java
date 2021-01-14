package pl.me.automation.utils;

import java.util.Properties;

public class Shop {

    private final String shopPageFindProduct;
    private final String shopPageAddProductsToBasket3;
    private final String shopPageAddProductsToBasket1;
    private final String shopPageAddProductsToBasket2;
    private final String shopPageAddProductsToWishListByIndex2;
    private final String shopPageAddProductsToWishListByIndex3;
    private final String shopPageClickProduct;
    private final String shopPageClickProduct3;
    private final String shopPageAddProductsToBasket4;
    private final String shopPageFindProductIncorrectFrase;
    private final String shopPageActiveFiltersDeleteByName1;
    private final String shopPageActiveFiltersDeleteByName2;
    private final String shopPageActiveFiltersDeleteByName3;
    private final String shopPageFilterProductsByColour;
    private final String shopPageFilterProductsBySize4;
    private final String shopPageFilterProductsBySize3;
    private final String shopPageSize;
    private final String shopPageFilterProductsBySize2;
    private final String shopPageFilterProductsBySize1;
    private final String shopPageFilterProductsByColour2;
    private final String shopPageSortProductsFromLowestPrice;
    private final String shopPageSortProductsFromHighestPrice;
    private final String shopPageSortProductsSortByAverageGrade;
    private final String shopPageAddProductsToBasket5;
    private final String shopPageGetJeansTornBlueJeans;
    private final String shopPageGetJeansBlueDenimJeans;
    private final String shopPageGetJeansBlackWashedJeans;
    private final String shopPageGetJeansBasicBlueJeans;
    private final String shopPageGetBlueDenimJeans;
    private final String shopPageGetBasicGrayBlueJeans;
    private final String shopPageGetTornBlueJeans;
    private final String shopPageGetBasicBlueJeans;



    public Shop(Properties properties) {
        shopPageFindProduct = properties.getProperty("shopPage.findProduct");
        shopPageAddProductsToBasket1 = properties.getProperty("shopPage.addProductsToBasket1");
        shopPageAddProductsToBasket2 = properties.getProperty("shopPage.addProductsToBasket2");
        shopPageAddProductsToBasket3 = properties.getProperty("shopPage.addProductsToBasket3");
        shopPageAddProductsToBasket4 = properties.getProperty("shopPage.addProductsToBasket4");
        shopPageAddProductsToBasket5 = properties.getProperty("shopPage.addProductsToBasket5");
        shopPageAddProductsToWishListByIndex2 = properties.getProperty("shopPage.addProductsToWishListByIndex2");
        shopPageAddProductsToWishListByIndex3 = properties.getProperty("shopPage.addProductsToWishListByIndex3");
        shopPageClickProduct = properties.getProperty("shopPage.clickProduct");
        shopPageClickProduct3 = properties.getProperty("shopPage.clickProduct3");
        shopPageFindProductIncorrectFrase = properties.getProperty("shopPage.findProductIncorrectFrase");
        shopPageActiveFiltersDeleteByName1 = properties.getProperty("shopPage.activeFiltersDeleteByName1");
        shopPageActiveFiltersDeleteByName2 = properties.getProperty("shopPage.activeFiltersDeleteByName2");
        shopPageActiveFiltersDeleteByName3 = properties.getProperty("shopPage.activeFiltersDeleteByName3");
        shopPageFilterProductsBySize1 = properties.getProperty("shopPage.filterProductsBySize1");
        shopPageFilterProductsBySize2 = properties.getProperty("shopPage.filterProductsBySize2");
        shopPageSize = properties.getProperty("shopPage.size");
        shopPageFilterProductsBySize3 = properties.getProperty("shopPage.filterProductsBySize3");
        shopPageFilterProductsBySize4 = properties.getProperty("shopPage.filterProductsBySize4");
        shopPageFilterProductsByColour = properties.getProperty("shopPage.filterProductsByColour");
        shopPageFilterProductsByColour2 = properties.getProperty("shopPage.filterProductsByColour2");
        shopPageSortProductsFromLowestPrice = properties.getProperty("shopPage.sortProductsFromLowestPrice");
        shopPageSortProductsFromHighestPrice = properties.getProperty("shopPage.sortProductsFromHighestPrice");
        shopPageSortProductsSortByAverageGrade = properties.getProperty("shopPage.sortProductsSortByAverageGrade");

        shopPageGetJeansTornBlueJeans = properties.getProperty("shopPage.getJeansTornBlueJeans");
        shopPageGetJeansBlueDenimJeans = properties.getProperty("shopPage.getJeansBlueDenimJeans");
        shopPageGetJeansBlackWashedJeans = properties.getProperty("shopPage.getJeansBlackWashedJeans");
        shopPageGetJeansBasicBlueJeans = properties.getProperty("shopPage.getJeansBasicBlueJeans");
        shopPageGetBlueDenimJeans = properties.getProperty("shopPage.getBlueDenimJeans");
        shopPageGetBasicGrayBlueJeans = properties.getProperty("shopPage.getBasicGrayBlueJeans");
        shopPageGetTornBlueJeans = properties.getProperty("shopPage.getTornBlueJeans");
        shopPageGetBasicBlueJeans = properties.getProperty("shopPage.getBasicBlueJeans");

    }

    public String getShopPageFindProduct() {
        return shopPageFindProduct;
    }

    public String getShopPageAddProductsToBasket3() {
        return shopPageAddProductsToBasket3;
    }

    public String getShopPageAddProductsToWishListByIndex3() {
        return shopPageAddProductsToWishListByIndex3;
    }

    public String getShopPageAddProductsToWishListByIndex2() {
        return shopPageAddProductsToWishListByIndex2;
    }
    public String getShopPageAddProductsToBasket1() {
        return shopPageAddProductsToBasket1;
    }


    public String getShopPageAddProductsToBasket2() {
        return shopPageAddProductsToBasket2;
    }


    public String getShopPageClickProduct() {
        return shopPageClickProduct;
    }


    public String getShopPageClickProduct3() {
        return shopPageClickProduct3;
    }


    public String getShopPageAddProductsToBasket4() {
        return shopPageAddProductsToBasket4;
    }


    public String getShopPageFindProductIncorrectFrase() {
        return shopPageFindProductIncorrectFrase;
    }


    public String getShopPageActiveFiltersDeleteByName1() {
        return shopPageActiveFiltersDeleteByName1;
    }

    public String getShopPageActiveFiltersDeleteByName2() {
        return shopPageActiveFiltersDeleteByName2;
    }

    public String getShopPageActiveFiltersDeleteByName3() {
        return shopPageActiveFiltersDeleteByName3;
    }

    public String getShopPageFilterProductsByColour() {
        return shopPageFilterProductsByColour;
    }

    public String getShopPageFilterProductsBySize4() {
        return shopPageFilterProductsBySize4;
    }

    public String getShopPageFilterProductsBySize3() {
        return shopPageFilterProductsBySize3;
    }

    public String getShopPageSize() {
        return shopPageSize;
    }

    public String getShopPageFilterProductsBySize2() {
        return shopPageFilterProductsBySize2;
    }

    public String getShopPageFilterProductsBySize1() {
        return shopPageFilterProductsBySize1;
    }

    public String getShopPageFilterProductsByColour2() {
        return shopPageFilterProductsByColour2;
    }


    public String getShopPageSortProductsFromLowestPrice() {
        return shopPageSortProductsFromLowestPrice;
    }

    public String getShopPageSortProductsFromHighestPrice() {
        return shopPageSortProductsFromHighestPrice;
    }

    public String getShopPageSortProductsSortByAverageGrade() {
        return shopPageSortProductsSortByAverageGrade;
    }

    public String getShopPageAddProductsToBasket5() {
        return shopPageAddProductsToBasket5;
    }


    public String getShopPageGetJeansTornBlueJeans() {
        return shopPageGetJeansTornBlueJeans;
    }

    public String getShopPageGetJeansBlueDenimJeans() {
        return shopPageGetJeansBlueDenimJeans;
    }

    public String getShopPageGetJeansBlackWashedJeans() {
        return shopPageGetJeansBlackWashedJeans;
    }

    public String getShopPageGetJeansBasicBlueJeans() {
        return shopPageGetJeansBasicBlueJeans;
    }

    public String getShopPageGetBlueDenimJeans() {
        return shopPageGetBlueDenimJeans;
    }

    public String getShopPageGetBasicGrayBlueJeans() {
        return shopPageGetBasicGrayBlueJeans;
    }

    public String getShopPageGetTornBlueJeans() {
        return shopPageGetTornBlueJeans;
    }

    public String getShopPageGetBasicBlueJeans() {
        return shopPageGetBasicBlueJeans;
    }


}
