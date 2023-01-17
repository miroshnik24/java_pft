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


public class ContactAddInGroupTest extends TestBase {

    private GroupData group;

    @BeforeMethod
    public void ensurePreconditions() {
//        if (app.db().contacts().size() == 0) {
//            app.goTo().HomePage();
//            app.contact().create( new ContactData().withFirstName( "firstname" ).withLastName( "lastname" ) );
//        }

        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create( new GroupData().withName( "test1" ) );
        }
    }


    @Test
    public void testContactAddToGroup() {

        ContactData contact = selectContactToTest();
        GroupData group = selectGroupToTest( contact );

        Groups contactGroupsBefore = app.db().contactById( contact.getId() ).getGroups();
        Contacts groupContactsBefore = app.db().contactsInGroupByName( group.getName() );

        app.goTo().HomePage();
        app.contact().addContactToGroup( contact, group );

        Groups contactGroupsAfter = app.db().contactById( contact.getId() ).getGroups();
        Contacts groupContactsAfter = app.db().contactsInGroupByName( group.getName() );

        assertEquals( contactGroupsAfter.size(), contactGroupsBefore.size() + 1 );
        assertThat( contactGroupsAfter, equalTo( contactGroupsBefore.withAdded( app.db().groupByName( group.getName() ) ) ) );

        assertEquals( groupContactsAfter.size(), groupContactsBefore.size() + 1 );
        assertThat( groupContactsAfter, equalTo( groupContactsBefore.withAdded( app.db().contactById( contact.getId() ) ) ) );

    }

    private ContactData selectContactToTest() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }

        app.goTo().HomePage();
        app.contact().create( new ContactData().withFirstName( "firstname" ).withLastName( "lastname" ) );
        Contacts contactsWithAded = app.db().contacts();

        return app.db().contactById( contactsWithAded.stream().mapToInt( (c) -> c.getId() ).max().getAsInt() );
    }


    private GroupData selectGroupToTest(ContactData contact) {
        Groups allGroups = app.db().groups();
        Groups contactGroups = app.db().contactById( contact.getId() ).getGroups();

        for (GroupData group : contactGroups) {
            allGroups.remove( group );
        }

        return allGroups.iterator().next();
    }
}

