package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactTestCase extends TestBase {


  @Test (enabled = true)
  public void testContact () {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Tatiana2", "Vladimirovna", "Miroshnik", "tatiana", "Test2");
    app.getContactHelper().createContact(contact);
//    app.getGroupHelper().wd.findElement(By.linkText("home page")).click();
//    app.getGroupHelper().wd.findElement(By.linkText("Logout")).click();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c1.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
