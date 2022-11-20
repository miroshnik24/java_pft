package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactTestCase extends TestBase {


  @Test (enabled = true)
  public void testContact () {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstname("Tatiana2").withLastname("Vladimirovna").withMiddlename("Miroshnik");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c1.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
