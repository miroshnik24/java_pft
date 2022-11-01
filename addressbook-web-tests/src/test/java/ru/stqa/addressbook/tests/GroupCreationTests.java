package ru.stqa.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  //  app.getGroupHelper().wd.findElement(By.linkText("Logout")).click();
  }

}
