package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Tatiana3").withMiddlename("Vladimirovna4")
              .withLastname("Miroshnik5"));
    }
  }

  @Test (enabled = true)
  public void testContactDeletion() throws Exception {

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.contact().switchTo();
    app.contact().homePage();
    Contacts after = app.db().contacts();

    assertThat(after.size(), equalTo(before.size() - 1));

    assertThat(after, equalTo(before.withOut(deletedContact)));


  }

}