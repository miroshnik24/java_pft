package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactTestCase extends TestBase {


  @Test
  public void testContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Tatiana", "Vladimirovna", "Miroshnik", "tatiana", "Test"));
//    app.getGroupHelper().wd.findElement(By.linkText("home page")).click();
//    app.getGroupHelper().wd.findElement(By.linkText("Logout")).click();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }


}
