package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @BeforeMethod (enabled = false)
  public void ensurePreconditions(){
    app.goTo().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
  }

  @Test (enabled = false)
  public  <T> String mergeAddress(ContactData contact) {
    return Stream.of(contact.getAddress()).filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
  
}
