package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase
{
    @BeforeMethod
    public void ensurePreconditions()
    {
        app.goTo().homePage();
        if(app.db().contacts().size() == 0)
        {
            File photo = new File("src/test/resources/Aleksandr_Golovin.jpg");
            ContactData cd = new ContactData().withFirstName("Aleksandr").withMiddleName("Sergeyevich").withLastName("Golovin").withNickname("Chick")
                    .withPhoto(photo).withTitle("Footballer")
                    .withCompany("AS Monaco FC").withAddress("Stade Louis II, Fontvielle, Monaco")
                    .withHomePhone("472-890").withMobilePhone("88002253535").withWorkPhone("123456").withFax("654321")
                    .withEmail("mail@mail.ru").withEmail2("mail1@mail.ru").withEmail3("mail2@mail.ru")
                    .withHomepage("www.asmonaco.com").withDayOfBirthday("30").withMonthOfBirthday("May").withYearOfBirthday("1996")
                    .withDayOfAnniversary("27").withMonthOfAnniversary("July").withYearOfAnniversary("2018")
                    .withAddress2("Kaltan, Russia").withPhone2("2-10-64").withNotes("He played for PFC CSKA Moscow.");

            app.goTo().newContactPage();
            app.contact().createContact(cd);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddress()
    {
        app.goTo().homePage();
        ContactData cd = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(cd);

        assertThat(cd.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
