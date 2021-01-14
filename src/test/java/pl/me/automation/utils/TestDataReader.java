package pl.me.automation.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class TestDataReader {

    private final String testDataLocation = "TestData.properties";
    private Properties properties;


    protected Contact contact;
    protected Payment payment;
    protected MyAccount myAccount;
    protected Shop shop;
    protected ShoppingCard shoppingCard;
    protected Home home;
    protected WishList wishList;
    protected SingleProduct singleProduct;

    public TestDataReader() {
        loadFile();
        loadData();
    }


    private void loadFile() {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("TestData.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is something wrong with configuration file." +
                    "File location" + getTestDataLocation());
        }

    }

    private void loadData() {
        contact = new Contact(properties);
        myAccount = new MyAccount(properties);
        payment = new Payment(properties);
        shop = new Shop(properties);
        shoppingCard = new ShoppingCard(properties);
        home = new Home(properties);
        wishList = new WishList(properties);
        singleProduct = new SingleProduct(properties);

    }


    public String getTestDataLocation() {
        return testDataLocation;
    }


}
