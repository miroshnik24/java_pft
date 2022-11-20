package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Set;

public class ContactTestCase extends TestBase {


  @Test (enabled = true)
  public void testContact () {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Tatiana2").withLastname("Vladimirovna").withMiddlename("Miroshnik");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
