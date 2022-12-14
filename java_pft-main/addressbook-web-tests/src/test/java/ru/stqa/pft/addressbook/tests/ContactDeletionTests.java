package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase
{
    @BeforeMethod
    public void ensurePreconditions()
    {
        app.goTo().homePage();
        if(app.db().contacts().size() == 0)
        {
            File photo = new File("src/test/resources/stru.jpg");
            ContactData cd = new ContactData().withFirstName("test1").withMiddleName("test2").withLastName("test3")
                    .withPhoto(photo).withHomePhone("111-111").withMobilePhone("89999999999").withWorkPhone("123456")
                    .withEmail("mail@mail.ru").withEmail2("mail1@mail.ru").withEmail3("mail2@mail.ru")
                    .withAddress2("testadress2").withPhone2("1-00-00");
            app.goTo().newContactPage();
            app.contact().createContact(cd);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() throws Exception
    {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().clickAlert();
        app.goTo().homePage();
        Contacts after = app.db().contacts();

        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(deletedContact)));

        verifyContactListInUI();
    }
}
