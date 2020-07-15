package pl.me.automation.domain;

import org.openqa.selenium.WebElement;

public class Product {
    private WebElement button;
    private String price;
    private Integer quantity;

    public Product(WebElement button, String price, Integer quantity) {
        this.button = button;
        this.price = price;
        this.quantity = quantity;
    }

    public WebElement getButton() {
        return button;
    }

    public String getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
