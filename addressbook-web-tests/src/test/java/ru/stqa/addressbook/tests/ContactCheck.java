package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCheck extends TestBase{


  @Test
  public void testContactAddress()
  {
    app.goTo().goToHomePage();
    ContactData cd = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(cd);

    assertThat(cd.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(cd.getAllEmail(), equalTo(contactInfoFromEditForm.mergeEmails()));
    assertThat(cd.getAllPhones(), equalTo(contactInfoFromEditForm.mergedPhones()));
  }


  public static String cleaned(String phone) {

    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
