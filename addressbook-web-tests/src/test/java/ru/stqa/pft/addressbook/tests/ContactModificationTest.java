package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().HomePage();
            app.contact().create(new ContactData("Иван", "Васильевич", "Иванов", null, "221-65-52", "89185555550","456-55-51",
                            "1.ru", "2.ru", "3.ru",null),true, groups.iterator().next().getName());
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData(modifiedContact.getId(),"Иван", "Васильевич", "Иванов", "г. Орел, ул. Левый берег реки Оки, д. 23", "221-65-52",
                "89185555550","5-22-22","1", "2", "3", null);
        app.goTo().HomePage();
        app.contact().modify(contact);
        app.goTo().HomePage();
        assertEquals(app.contact().count(),before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
