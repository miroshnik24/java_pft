package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromXml")
    public void testGroupCreation(GroupData group) {
        app.goTo().GroupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        app.goTo().returnToGroupPage();
        assertEquals(app.group().count(),before.size() + 1);
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
        verifyGroupListInUI();
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().GroupPage();
        Groups before = app.db().groups();
        GroupData group = new GroupData("test1'", "test2", "test3");
        app.group().create(group);
        app.goTo().returnToGroupPage();
        assertEquals(app.group().count(),before.size());
        Groups after = app.db().groups();
        assertThat(after, equalTo(before));
        verifyGroupListInUI();
    }

}
