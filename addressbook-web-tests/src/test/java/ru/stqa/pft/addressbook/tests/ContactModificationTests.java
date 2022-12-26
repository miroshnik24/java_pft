package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase
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
        }
    }

    public void ensurePreconditions(String group)
    {
        app.goTo().groupPage();
        if(!app.group().isThereAGroupByName(group))
        {
            GroupData gd = new GroupData().withName(group);
            app.group().create(gd);
        }
    }

    @Test
    public void testContactModification()
    {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/chalov.jpg");
        ContactData cd = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Fyodor").withMiddleName("Nikolayevich").withLastName("Chalov").withNickname("Esthete")
                .withPhoto(photo).withTitle("Footballer").withCompany("PFC CSKA Moscow")
                .withAddress("Russia, Moscow, VEB Arena").withHomePhone("21063").withMobilePhone("89999999999")
                .withWorkPhone("1234567890").withFax("100").withEmail("f@c.ru").withEmail2("f2@c.ru").withEmail3("f3c.ru")
                .withHomepage("https://www.pfc-cska.com/").withDayOfBirthday("10").withMonthOfBirthday("April").withYearOfBirthday("1998")
                .withDayOfAnniversary("21").withMonthOfAnniversary("September").withYearOfAnniversary("2016")
                .withGroupName("Joker").withAddress2("Moscow").withPhone2("1-2-3").withNotes("The best footballer of Russia in 2017");
        ensurePreconditions(cd.getGroupName());
        app.goTo().homePage();
        app.contact().modify(cd);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(cd)));

        verifyContactListInUI();
    }
}
