package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int before  = app.getContactHelper().getContactCount ();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Tatiana", "Vladimirovna", "Miroshnik", "tatiana", "Test", "Test2", "Novosibirsk", "89511111111", "test@test.ru"));
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().fillContactFrom(new ContactData("Tatiana1", "Vladimirovna3", "Miroshnik", "tatiana", "Test", "Test2", "Test", "89511111000", "test@test1.ru"));
    app.getContactHelper().submitContactModificationSave();
    app.getContactHelper().goToHomePage();
    int after  = app.getContactHelper().getContactCount ();
    Assert.assertEquals(after, before);
  }

}
