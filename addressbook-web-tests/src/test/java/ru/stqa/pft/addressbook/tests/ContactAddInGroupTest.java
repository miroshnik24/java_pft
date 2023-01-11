package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddInGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData("test1", "test2", "test3"));
            app.goTo().returnToGroupPage();
        }

        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().HomePage();
            app.contact().create(new ContactData("Иван", "Васильевич", "Иванов", null
                    , "221-65-52", "89185555550", "5555555", "456-55-51",
                    "1.ru", "2.ru", "3.ru",null),true, groups.iterator().next().getName());
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactAddInGroup() {
        app.goTo().HomePage();
        ContactData addedContact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        Contacts before = group.getContacts();
        app.contact().addInGroup(addedContact, group);
        app.goTo().GroupPageByName(group.getName());
        GroupData groupAfter = app.db().groupById(group.getId()).iterator().next();
        Contacts after = groupAfter.getContacts();
        assertThat(after, equalTo(before.without(addedContact).withAdded(addedContact)));
        verifyContactListInGroupInUI(groupAfter);
    }
}
