package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getContactHelper().selectContact();
 //   app.getGroupHelper().selectGroup();
    app.getContactHelper().deleteSelectedContact();
    app.switchTo();
    app.getGroupHelper().returnToHomePage();
  }
}