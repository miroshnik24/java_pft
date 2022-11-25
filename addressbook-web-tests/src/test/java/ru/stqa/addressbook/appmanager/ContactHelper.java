package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

 // private WebDriver wd;

  public ContactHelper(WebDriver wd) {
    super(wd);
 //   this.wd = wd;
  }

  public void addContactClick() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactFrom(ContactData contactData) {
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

  public void selectContactById(int id) {
    wd.findElement(By.xpath("//input[@id='" + id+ "']")).click();
  }

  public void selectContactModification(int id) {
    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr['" + id+ "']/td[8]/a/img")).click();
  }

  public void submitContactModification(int i) {
    click(By.xpath("//img[@alt='Edit']"));
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
    fillContactFrom(contact);
    addContactClick();
    homePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    selectContactModification(contact.getId());
    fillContactFrom(contact);
    submitContactModificationSave();
    homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    switchTo();
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
      String firstname = element.getText();
      //String middlename = element.getText();
      String lastname = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname)
              //.withMiddlename(middlename)
              .withLastname(lastname));
    }
    return contacts;
  }

}