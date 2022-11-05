package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
   // app.getNovigationHelper().gotoGroupPage();
  //  app.getContactHelper().selectContact();
    app.getContactHelper().submitContactModification();
    app.getContactHelper().fillContactFrom(new ContactData("Tatiana", "Vladimirovna2", "Miroshnik", "tatiana", "Test", "Test2", "Test", "89511111000", "test@test1.ru"));
    app.getContactHelper().submitContactModificationSave();
    app.getGroupHelper().returnToHomePage();
  }

}
