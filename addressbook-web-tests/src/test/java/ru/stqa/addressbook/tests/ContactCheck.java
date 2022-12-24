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
    app.goTo().goToHomePage();
   // ContactData contact = app.contact().all().iterator().next();
    //ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }

  @Test
  public void testContactPhones()
  {
    app.goTo().homePage();
    ContactData cd = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(cd);

    assertThat(cd.getAllPhones(), equalTo(contactInfoFromEditForm.mergedPhones()));
  }

  public static String cleaned(String phone)
  {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  @Test
  public void testContactAddress()
  {
    app.goTo().homePage();
    ContactData cd = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(cd);

    assertThat(cd.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  @Test
  public void testContactAddress()
  {
    app.goTo().homePage();
    ContactData cd = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(cd);

    assertThat(cd.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

}
