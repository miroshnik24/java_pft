package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactTestCase extends TestBase {


  @Test
  public void testContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Tatiana", "Vladimirovna", "Miroshnik", "tatiana", "Test2");
    app.getContactHelper().createContact(contact);
//    app.getGroupHelper().wd.findElement(By.linkText("home page")).click();
//    app.getGroupHelper().wd.findElement(By.linkText("Logout")).click();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }


}
