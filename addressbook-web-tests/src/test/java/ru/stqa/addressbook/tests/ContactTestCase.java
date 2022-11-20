package ru.stqa.addressbook.tests;

import org.hamcrest.junit.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactTestCase extends TestBase {


  @Test (enabled = true)
  public void testContact () {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Tatiana2").withLastname("Vladimirovna").withMiddlename("Miroshnik");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
