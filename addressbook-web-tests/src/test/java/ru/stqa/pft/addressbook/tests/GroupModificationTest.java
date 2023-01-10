package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups g = app.db().groups();
        int c = app.db().groups().size();
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData("test1", "test2", "test3"));
            app.goTo().returnToGroupPage();
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData ModifiedGroup = before.iterator().next();
        GroupData group = new GroupData(ModifiedGroup.getId(),"test1", "test5", "test6");
        app.goTo().GroupPage();
        app.group().modify(group);
        app.goTo().returnToGroupPage();
        assertEquals(app.group().count(),before.size());
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(ModifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }

}
