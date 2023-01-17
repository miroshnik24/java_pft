package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTest extends TestBase {
    @BeforeMethod
   public void ensurePreconditions() {
    app.goTo().HomePage();
//    if (app.contact().all().size() == 0) {
//        app.contact().create(new ContactData()
//                .withFirstName("test").withLastName("test") );
//    }
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create( new GroupData().withName( "test1" ) );
        }
}

    @Test
    public void testContactInfo() {
        app.goTo().HomePage();

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
