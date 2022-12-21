package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCheck extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
//    ContactData contact = app.contact().all().iterator().next();
//    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }

  @Test
  public  <T> String mergePhones(ContactData contact) {
    assertThat(contact.getAllPhones(), equalTo(mergePhones(app.contact().infoFromEditForm(contact))));
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(),contact.getWorkPhone(),contact.getPhone2())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactCheck::cleaned)
            .collect(Collectors.joining("\n"));
  }
  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }


  @Test
  public  <T> String mergeEmail(ContactData contact) {
    assertThat(contact.getAllEmails(), equalTo(mergeEmail(app.contact().infoFromEditForm(contact))));
    return Arrays.asList(contact.getEmail(), contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  @Test
  public String mergeAddress(ContactData contact) {
    assertThat(contact.getAddress(), equalTo(mergeAddress(app.contact().infoFromEditForm(contact))));
    return Stream.of(contact.getAddress()).filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
