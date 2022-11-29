package ru.stqa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

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
//    Contacts before = app.contact().all();
//    ContactData deletedContact = before.iterator().next();
//    app.contact().delete(deletedContact);
//    Contacts after = app.contact().all();
//    assertEquals(after.size(), before.size() - 1);
//    assertThat(after, equalTo(before.withOut(deletedContact)));

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.contact().switchTo();
    app.contact().homePage();
    Contacts after = app.contact().all();

    assertThat(after.size(), equalTo(before.size() - 1));

    assertThat(after, equalTo(before.withOut(deletedContact)));


  }

}