package pl.me.automation;

import pl.me.automation.page.PaymentPage;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class PaymentForm {

    public void fillPaymentForm(PaymentPage paymentPage){
        LocalDateTime now = LocalDateTime.now();
        paymentPage.enterBillingUserName("rafałł");
        paymentPage.enterBillingUserLastName("kwiatkowski");
        paymentPage.selectBillingCountry(1);
        paymentPage.enterBillingUserAddress("Kwiatowa");
        paymentPage.enterBillingUserCity("Poznań");
        paymentPage.enterBillingUserPostCode("60001");
        paymentPage.enterBillingUserPhone("600500400");
        paymentPage.enterBillingUserEmail("qwert!#11"+ now.toString().replaceAll(":","-")+"@nazwa.pl");;
        paymentPage.enterBillingAccountUsername("zxcvbn"+ UUID.randomUUID().toString());
        paymentPage.enterBillingAccountPassword("&%SIytrdf!90" + now.toString().replaceAll(":", "-"));
    }

    public void fillPaymentFormOtherAddress(PaymentPage paymentPage){
        paymentPage.enterShippingUserName("rafał");
        paymentPage.enterShippingUserLastName("kwiatkowski");
        paymentPage.selectShippingCountry(3);
        paymentPage.enterShippingAddress("Kwiatowa");
        paymentPage.enterShippingCity("Gdańsk");
        paymentPage.enterShippingRegion("pomorskie");
        paymentPage.enterShippingPostCode("50000");
    }

    public void fillPaymentFormOtherAddressIncorrectly(PaymentPage paymentPage){
        paymentPage.enterShippingUserName("rafał");
        paymentPage.enterShippingUserLastName("kwiatkowski");
        paymentPage.selectShippingCountry(3);
        paymentPage.enterShippingAddress("Kwiatowa");
        paymentPage.enterShippingCity("Gdańsk");
        paymentPage.enterShippingRegion("pomorskie");
        paymentPage.enterShippingPostCode("%");
    }
}
