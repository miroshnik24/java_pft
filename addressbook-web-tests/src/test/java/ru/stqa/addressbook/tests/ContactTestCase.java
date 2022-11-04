package ru.stqa.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.addressbook.model.ContactData;

public class ContactTestCase extends TestBase {


  @Test
  public void testContact() throws Exception {
    app.getContactHelper().addContact();
    app.getContactHelper().fillContactFrom(new ContactData("Tatiana", "Vladimirovna", "Miroshnik", "tatiana", "Test", "Test2", "Novosibirsk", "89511111111", "test@test.ru"));
    app.getContactHelper().addContactClick();
    app.getGroupHelper().wd.findElement(By.linkText("home page")).click();
    app.getGroupHelper().wd.findElement(By.linkText("Logout")).click();
  }


}
