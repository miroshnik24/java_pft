package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test (enabled = true)
  public void testContactDeletion() {
    app.goTo().goToHomePage();
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Tatiana", "Vladimirovna", "Miroshnik", "tatiana", "Test"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.switchTo();
  //  app.getNovigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
    }
}