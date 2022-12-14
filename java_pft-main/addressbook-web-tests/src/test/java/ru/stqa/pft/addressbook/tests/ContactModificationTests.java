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
            File photo = new File("src/test/resources/stru.jpg");
            ContactData cd = new ContactData().withFirstName("test1").withMiddleName("test12").withLastName("test13").withNickname("test14")
                    .withPhoto(photo).withTitle("test15")
                    .withCompany("test15").withAddress("Stest16")
                    .withHomePhone("111-111").withMobilePhone("811111111111").withWorkPhone("123456").withFax("654321")
                    .withEmail("mail@test.ru").withEmail2("mail1@test2.ru").withEmail3("mail2@test3.ru")
                    .withHomepage("ttest1").withDayOfBirthday("30").withMonthOfBirthday("May").withYearOfBirthday("1996")
                    .withDayOfAnniversary("27").withMonthOfAnniversary("July").withYearOfAnniversary("2018")
                    .withAddress2("test15").withPhone2("2-22-22").withNotes("Htest1");

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
        File photo = new File("src/test/resources/stru.jpg");
        ContactData cd = new ContactData().withId(modifiedContact.getId())
                .withFirstName("test12").withMiddleName("test2").withLastName("test21").withNickname("test22")
                .withPhoto(photo).withTitle("test23").withCompany("test24")
                .withAddress("test25").withHomePhone("262223").withMobilePhone("89999999999")
                .withWorkPhone("1234567890").withFax("123231").withEmail("1@st.ru").withEmail2("2@st.ru").withEmail3("3c.ru")
                .withHomepage("test25").withDayOfBirthday("30").withMonthOfBirthday("April").withYearOfBirthday("1999")
                .withDayOfAnniversary("11").withMonthOfAnniversary("September").withYearOfAnniversary("2011")
                .withGroupName("test2").withAddress2("test256").withPhone2("1-2-3").withNotes("test2343");
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
