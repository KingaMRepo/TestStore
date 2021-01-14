package pl.me.automation.utils;

import java.util.Properties;

public class Contact  {
    private final String contactPageEnterUserLastName;
    private final String contactPageEnterUserName;
    private final String contactPageSelectMessageSubject;
    private final String contactPageEnterEmailAddress;
    private final String contactPageEnterMessage;
    private final String contactPageEnterIncorrectEmailAddress;

    public Contact(Properties properties) {
        contactPageEnterUserName = properties.getProperty("contact.enterUserName");
        contactPageEnterUserLastName = properties.getProperty("contact.enterUserLastName");
        contactPageSelectMessageSubject = properties.getProperty("contact.selectMessageSubject");
        contactPageEnterEmailAddress = properties.getProperty("contact.enterEmailAddress");
        contactPageEnterMessage = properties.getProperty("contact.enterMessage");
        contactPageEnterIncorrectEmailAddress = properties.getProperty("contactPage.enterIncorrectEmailAddress");
    }

    public String getContactPageEnterUserLastName() {
        return contactPageEnterUserLastName;
    }

    public String getContactPageEnterUserName() {
        return contactPageEnterUserName;
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

    public String getContactPageEnterIncorrectEmailAddress() {
        return contactPageEnterIncorrectEmailAddress;
    }




}
