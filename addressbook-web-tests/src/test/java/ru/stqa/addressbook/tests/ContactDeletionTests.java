package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNovigationHelper().goToHomePage();
    int before  = app.getContactHelper().getContactCount ();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Tatiana", "Vladimirovna", "Miroshnik", "tatiana", "Test", "Test2", "Novosibirsk", "89511111111", "test@test.ru"));
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteSelectedContact();
    app.switchTo();
  //  app.getNovigationHelper().goToHomePage();
    int after  = app.getContactHelper().getContactCount ();
    Assert.assertEquals(after, before - 1);
  }
}