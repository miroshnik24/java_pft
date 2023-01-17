package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ContactDeleteFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
        app.goTo().GroupPage();
        app.group().create(new GroupData("test1", "test2", "test3"));
        app.goTo().returnToGroupPage();
    }
//        if (app.db().contacts().size() == 0) {
//            Groups groups = app.db().groups();
//            app.goTo().HomePage();
//            app.contact().create(new ContactData("Иван", "Васильевич", "Иванов", null
//                    , "221-65-52", "89185555550", "22222222", "456-55-51",
//                    "1.ru", "2.ru", "3.ru",null),true, groups.iterator().next().getName());
//            app.goTo().HomePage();
//        }

    }



    @Test
    public void testDeleteContactFromGroup() {

        GroupData group = selectGroupToTest();
        Contacts groupContactsBefore = app.db().contactsInGroupByName(group.getName());

        ContactData contact = groupContactsBefore.iterator().next();
        Groups contactGroupsBefore = app.db().contactById(contact.getId()).getGroups();


        app.goTo().groupPage(group);
        app.contact().deleteContactFromGroup(contact);


        Contacts groupContactsAfter = app.db().contactsInGroupByName(group.getName());
        Groups contactGroupsAfter = app.db().contactById(contact.getId()).getGroups();


        assertEquals(contactGroupsAfter.size(), contactGroupsBefore.size() - 1);
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withOut(app.db().groupByName(group.getName()))));

        assertEquals(groupContactsAfter.size(), groupContactsBefore.size() - 1);
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.withOut(app.db().contactById(contact.getId()))));

    }

    private GroupData selectGroupToTest() {
        Groups groups = app.db().groups();

        for (GroupData group : groups) {
            if (app.db().contactsInGroupByName(group.getName()).size() > 0) {
                return group;
            }
        }

        GroupData groupForTest = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        app.contact().addContactToGroup(contacts.iterator().next(), groupForTest);
        app.goTo().HomePage();
        return groupForTest;
    }
}
