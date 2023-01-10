package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().HomePage();
            app.contact().create(new ContactData("Иван", "Васильевич", "Иванов", null, "221-65-52", "89185555550","456-55-51",
                    "1.ru", "2.ru", "3.ru",null),true, groups.iterator().next().getName());
            app.goTo().HomePage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData("test1", "test2", "test3"));
            app.goTo().returnToGroupPage();
        }



    }

    @Test
    public void testContactDeleteFromGroup() {
        GroupData groupBefore = app.db().groups().iterator().next();
        if (groupBefore.getContacts().size() == 0) {
            app.contact().addInGroup(app.db().contacts().iterator().next(), groupBefore);
            groupBefore = app.db().groupById(groupBefore.getId()).iterator().next();
        }
        ContactData deletedContact = groupBefore.getContacts().iterator().next();
        app.goTo().HomePage();
        Contacts before = groupBefore.getContacts();
        app.contact().deleteFromGroup(deletedContact, groupBefore);
        app.goTo().GroupPageByName(groupBefore.getName());
        GroupData groupAfter = app.db().groupById(groupBefore.getId()).iterator().next();
        Contacts after = groupAfter.getContacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInGroupInUI(groupAfter);
    }
}
