package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTest extends TestBase {

    @Test
    public void testContactInfo() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            Groups groups = app.db().groups();
            app.contact().create(new ContactData("Тест", "Тест2", "Тест3", null
                    , "221-65-52", "800000000", "1111111", "000-00-00",
                             "Email1", "Email2", "Email3", null),true, groups.iterator().next().getName());
            app.goTo().HomePage();
        }

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(contactInfoFromEditForm.mergePhones()));
        assertThat(contact.getAddres(), equalTo(contactInfoFromEditForm.getAddres()));
        assertThat(contact.getAllEmails(), equalTo(contactInfoFromEditForm.mergeEmails()));
    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
