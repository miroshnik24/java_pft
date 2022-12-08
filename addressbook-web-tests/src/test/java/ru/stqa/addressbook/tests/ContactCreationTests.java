package ru.stqa.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line !=null){
        json+=line;
        line=reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((c)-> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();

  }

}