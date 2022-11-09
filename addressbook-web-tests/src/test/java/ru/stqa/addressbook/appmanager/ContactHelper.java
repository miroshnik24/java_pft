package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  private WebDriver wd;

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
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
  //  click(By.name("theform"));
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
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


  public void selectContact() {
    click(By.name("selected[]"));
  }


  public void submitContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModificationSave() {
    click(By.xpath("//div[4]/form/input"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }


  public void createContact(ContactData contact) {
    goToHomePage();
    addContact();
    fillContactFrom(contact);
    addContactClick();
  }

  public void goToHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return  isElementPresent(By.name("selected[]"));
//    return false;
  }
}