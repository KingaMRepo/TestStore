package pl.me.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SingleProductPage<webDriver> extends Menu {

    @FindBy(css = "div[class~='woocommerce-product-gallery']")
    private WebElement singeProductImage;
    @FindBy(className = "woocommerce-product-gallery__trigger")
    private WebElement productZoom;
    @FindBy(xpath = "//button[contains(@class, 'button--arrow--right')]")
    private WebElement productbuttonArraw;
    @FindBy(xpath = "//button[contains(@class, 'button--close')]")
    private WebElement closeButton;
    @FindBy(css = ".woocommerce-breadcrumb +h1")
    private WebElement singleProductNameText;
    @FindBy(css = "div[class~='owl-wrapper-outer']>div>div:first-child>li>.astra-shop-thumbnail-wrap+.astra-shop-summary-wrap>a>h2")
    private WebElement recommendProductNameText;
    @FindBy(css = "[class~='add_to_cart_button']")
    private WebElement recommendProductButton;
    @FindBy(css = "#tab-title-reviews>a")
    private WebElement reviewsButton;
    @FindBy(css = ".stars>span>a")
    private List<WebElement> starRatingList;
    @FindBy(id = "comment")
    private WebElement commentTextArea;
    @FindBy(id = "author")
    private WebElement commentAuthor;
    @FindBy(id = "email")
    private WebElement authorEmail;
    @FindBy(id = "submit")
    private WebElement addCommentSubmitButton;
    @FindBy(css = ".description>p")
    private WebElement commentDescription;
    @FindBy(css = ".comment-text>.star-rating")
    private WebElement addedStarRating;
    @FindBy(css = ".woocommerce-noreviews")
    private WebElement noReviewsText;
    @FindBy(css = "p#wc-stripe-payment-request-button-separator+button")
    private WebElement addToCardSingleProductButton;
    @FindBy(css = "h1[class~='product_title']")
    private WebElement singleProductName;
    @FindBy(css = ".cart-container")
    private WebElement widgetIcon;
    @FindBy(css = ".star-rating + a")
    private List<WebElement> recommendedProductsButtons;
    @FindBy(css = ".astra-shop-summary-wrap>a>h2")
    private List<WebElement> recommendedProductsNames;




    JavascriptExecutor js = (JavascriptExecutor) webDriver;


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

    public Boolean isZoomImageDisplayed() {
        return productZoom.isEnabled();
    }

    public String getSingleProductNameText() {
        return singleProductNameText.getText();
    }

    public String getRecommendProductNameText() {
        return recommendProductNameText.getText();
    }

    public void clickRecommendProductButton() {
        recommendProductButton.click();
    }

    public SingleProductPage clickAddToCardSingeProductButton() {
        addToCardSingleProductButton.click();
        return new SingleProductPage(webDriver);
    }


    public ShoppingCardPage clickWidget() {
        widgetIcon.click();
        return new ShoppingCardPage(webDriver);
    }


    public void clickReviewsButton() {
        reviewsButton.click();
    }

    public SingleProductPage addStarRating(String starRatingNumber) {

        for (int i = 0; i < starRatingList.size(); i++) {
            if (starRatingList.get(i).getAttribute("class").equals(starRatingNumber)) {
                starRatingList.get(i).click();
            }
        }
        return new SingleProductPage(webDriver);
    }

    public void enterUserNameAndEmail(String name, String email) {
        commentAuthor.clear();
        commentAuthor.sendKeys(name);
        authorEmail.clear();
        authorEmail.sendKeys(email);
    }

    public void enterComment(String comment) {
        commentTextArea.clear();
        commentTextArea.sendKeys(comment);
    }


    public void clickCommentSubmitButton() {
        addCommentSubmitButton.click();
    }


    public String getCommentDescription() {
        return commentDescription.getText();
    }

    public String getAddedStarRating() {
        return addedStarRating.getText();
    }

    public String getAlertBoxText() {
        String text = webDriver.switchTo().alert().getText();

        return text;
    }


    public void clickAlertBoxAccept() {
        webDriver.switchTo().alert().accept();
    }


    public String getNoReviewsText() {
        return noReviewsText.getText();
    }


    public String getSingleProductName() {
        return singleProductName.getText();

    }


    public SingleProductPage clickRecommendedProductsButton(Integer index) {
        recommendedProductsButtons.get(index).click();
        return new SingleProductPage(webDriver);
    }

    public List<String> getRecommendedProductsName() {
        List<String> addedRecommendedProductsNames = new ArrayList<>();
        for (int i = 0; i < recommendedProductsNames.size(); i++) {
            recommendedProductsNames.get(i).getText();
        }
        return addedRecommendedProductsNames;

    }


}















