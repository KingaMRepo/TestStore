package pl.me.automation.domain;

import org.openqa.selenium.WebElement;

public class Product {
    private WebElement button;
    private String price;
    private Integer quantity;
    private String name;

    public Product(WebElement button, String price, Integer quantity, String name) {
        this.button = button;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public Product() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
