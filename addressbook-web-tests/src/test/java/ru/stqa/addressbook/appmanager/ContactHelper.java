package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

 // private WebDriver wd;

  public ContactHelper(WebDriver wd) {
    super(wd);
 //   this.wd = wd;
  }

  public void addContactClick() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactFrom(ContactData contactData, boolean b) {
    type(By.name("firstname"), contactData.getFirstname());
    // wd.findElement(By.name("theform")).click();
   // type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
//    type(By.name("nickname"), contactData.getNickname());
//    type(By.name("title"), contactData.getTitle());
//    type(By.name("company"), contactData.getCompany());
//    type(By.name("address"), contactData.getAddress());
//  //  click(By.name("theform"));
//    type(By.name("mobile"), contactData.getMobile());
//    type(By.name("email"), contactData.getEmail());
  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void addContact() {
    click(By.linkText("add new"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }


  public void selectContactById(int id)
  {
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element : elements)
    {
      WebElement checkbox = element.findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));

      if(id == Integer.parseInt(checkbox.getAttribute("value")))
      {
        checkbox.click();
        return;
      }
    }
  }

  public void submitContactModification(int id)
  {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))). click();
  }

  public void submitContactModificationSave() {
    click(By.xpath("//div[4]/form/input"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void homePage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    homePage();
    addContact();
    fillContactFrom(contact, false);
    addContactClick();
    homePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    submitContactModification(contact.getId());
    fillContactFrom(contact, false);
   // fillContactFrom(contact, false);
    submitContactModificationSave();
    homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    //switchTo();
  }

  public void switchTo() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return !isElementPresent(By.name("selected[]"));
//    return false;
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> contactData = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(contactData.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = contactData.get(1).getText();
      String firstname = contactData.get(2).getText();
      String AllPhones = contactData.get(5).getText();
      String AllEmail = contactData.get(4).getText();
      // int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(AllPhones).withAllEmail(AllEmail);
              //withMiddlename(middlename)

      contacts.add(contact);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    submitContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2)
            .withEmail3(email3);
  }
}