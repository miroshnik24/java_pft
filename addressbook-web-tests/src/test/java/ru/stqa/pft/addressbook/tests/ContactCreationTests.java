package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase
{
    public void ensurePreconditions(String group)
    {
        app.goTo().groupPage();
        if(!app.group().isThereAGroupByName(group))
        {
            GroupData gd = new GroupData().withName(group);
            app.group().create(gd);
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml"))))
        {
            String xml = "";
            String line = reader.readLine();
            while (line != null)
            {
                xml += line;
                line = reader.readLine();
            }
            reader.close();

            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json"))))
        {
            String json = "";
            String line = reader.readLine();
            while (line != null)
            {
                json += line;
                line = reader.readLine();
            }

            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>()
            {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) throws Exception
    {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ensurePreconditions(contact.getGroupName());
        app.goTo().newContactPage();
        app.contact().createContact(contact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));

        verifyContactListInUI();
    }
}
