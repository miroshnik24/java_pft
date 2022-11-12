package ru.stqa.addressbook.tests;

//import org.testng.Assert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

//import static org.testng.Assert.*;

public class GroupDeletionTests extends TestBase {

  @Test

    public void testGroupDeletion() {
    app.getNovigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);

  }



}
