package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  private ContactData contact;
//
//  @DataProvider
//  public Iterator<Object[]> validContactsFromJson() throws IOException {
//    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
//    String json = "";
//    String line = reader.readLine();
//    while (line != null) {
//      json += line;
//      line = reader.readLine();
//    }
//    Gson gson = new Gson();
//    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
//    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
//  }
//
////  @Test (dataProvider = "validContactsFromJson")
////  public void testContact () {
////    Contacts before = app.contact().all();
////    File photo = new File("src/test/resources/stru.png");
////    ContactData contact = new ContactData()
////            .withFirstname("Tatiana").withMiddlename("Vladimirovna").withLastname("Miroshnik").withPhoto(photo);
////    app.contact().create(contact);
////    Contacts after = app.contact().all();
////    assertThat(after.size(), equalTo(before.size() + 1));
////
////    assertThat(after, equalTo(
////            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
////  }
//
//  @Test (dataProvider = "validContactsFromJson")
//  public void testContactsCreation(GroupData group) {
//    app.goTo().goToHomePage();
//    Contacts before = app.contact().all();
//    app.contact().create(contact);
//    //assertThat(app.contact().count(), equalTo(before.size() + 1));
//    Contacts after = app.contact().all();
//    assertThat(after.size(), equalTo(before.size() + 1));
//    assertThat(after, equalTo(
//            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
//  }

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
    app.contact().create(contact);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    verifyContactListInUI();
  }

}
