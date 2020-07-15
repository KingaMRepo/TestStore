package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SingleProductPage extends Menu  {

    @FindBy(css="div[class~='woocommerce-product-gallery']")
    private WebElement singeProductImage;
    @FindBy(className = "woocommerce-product-gallery__trigger")
    private WebElement productZoom;
    @FindBy(xpath = "//button[contains(@class, 'button--arrow--right')]")
    private WebElement productbuttonArraw;
    @FindBy(xpath = "//button[contains(@class, 'button--close')]")
    private WebElement closeButton;
    @FindBy(css=".woocommerce-breadcrumb +h1")
    private WebElement singleProductNameText;
    @FindBy(css="div[class~='owl-wrapper-outer']>div>div:first-child>li>.astra-shop-thumbnail-wrap+.astra-shop-summary-wrap>a>h2")
    private WebElement recommendProductNameText;
    @FindBy(css="[class~='add_to_cart_button']")
    private WebElement recommendProductButton;
    @FindBy(css=".quantity+button")
    private WebElement addToCardSingeProductButton;


    public SingleProductPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(singeProductImage));
    }

    public void ZoomProductPhoto() {
        productZoom.click();
        wait.until(ExpectedConditions.elementToBeClickable(productbuttonArraw));
        productbuttonArraw.click();
        wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeButton.click();
    }

    public Boolean isZoomImageDisplayed(){
        return productZoom.isEnabled();
    }

    public String getSingleProductNameText(){
        return singleProductNameText.getText();
    }

    public String getRecommendProductNameText(){
        return recommendProductNameText.getText();
    }

    public void clickRecommendProductButton(){
        recommendProductButton.click();
    }

    public void clickAddToCardSingeProductButton(){
        addToCardSingeProductButton.click();
    }

}
