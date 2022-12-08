package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Tatiana2").withLastname("Miroshnik"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("test1")
            .withLastname("test2").withMobilePhone("9999999").withAddress("Address3");
    app.goTo().homePage();
    app.contact().modify(contact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size()));

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

}
