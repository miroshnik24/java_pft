package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.addressbook.tests.TestBase.app;

public class CreationContactToGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("test1").withLastname("test2").withEmail("test@test.test")
              .withMobilePhone("111111"), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test11").withFooter("Test22").withHeader("Test33"));
    }
  }

  @Test
  public void testAddContact() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData addedContact = contacts.iterator().next();
    GroupData toGroup = groups.iterator().next();
    Groups before = addedContact.getGroups();
    Contacts groupContacts = toGroup.getContacts();
    if (groupContacts.contains(addedContact)) {
      for (GroupData group : groups.without(toGroup)) {
        Contacts contactsInGroup = group.getContacts();
        if (!contactsInGroup.contains(addedContact)) {
          toGroup = group;
        }
      }
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test11").withFooter("Test22").withHeader("Test33"));
      for (GroupData groupNew : app.db().groups()) {
        if (groupNew.getName().equals("Test11")) {
          toGroup = groupNew;
        }
      }
    }
    app.goTo().homePage();
    app.contact().addToGroup(addedContact, toGroup);
    Groups after = addedContact.getGroups();
    assertThat(before, equalTo(after));
  }
}
