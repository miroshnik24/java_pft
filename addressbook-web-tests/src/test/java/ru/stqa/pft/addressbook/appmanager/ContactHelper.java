package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static java.lang.Integer.parseInt;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactHelper extends BaseHelper
{
    private Contacts contactsCache = null;

    public ContactHelper(WebDriver wd)
    {
        super(wd);
    }

    public Contacts all()
    {
        if(contactsCache != null)
        {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement e : elements)
        {
            List<WebElement> contactData = e.findElements(By.tagName("td"));
            int id = parseInt(contactData.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = contactData.get(1).getText();
            String firstName = contactData.get(2).getText();
            String allPhones = contactData.get(5).getText();
            String address = contactData.get(3).getText();
            String allEmails = contactData.get(4).getText();
            ContactData cd = new ContactData().withId(id).withLastName(lastName).withFirstName(firstName)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails);

            contactsCache.add(cd);
        }

        return new Contacts(contactsCache);
    }

    public void selectContactById(int id)
    {
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement e : elements)
        {
            WebElement checkbox = e.findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));

            if(id == parseInt(checkbox.getAttribute("value")))
            {
                checkbox.click();
                return;
            }
        }
    }

    public void createContact(ContactData cd, boolean b)
    {
        fillContactForm(cd, true);
        contactsCache = null;
        submitContactCreation();
    }

    public void modify(ContactData contact)
    {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        contactsCache = null;
        submitContactModification();
    }
    public void delete(ContactData contact)
    {
        selectContactById(contact.getId());
        contactsCache = null;
        deleteSelectedContacts();
    }

    public void fillContactForm(ContactData contactData, boolean creation)
    {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());

        if(contactData.getPhoto() == null && !creation)
        {
            click(By.name("delete_photo"));
        }
        else
        {
            attach(By.name("photo"), contactData.getPhoto());
        }

        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        select(By.name("bday"), contactData.getDayOfBirthday());
        select(By.name("bmonth"), contactData.getMonthOfBirthday());
        type(By.name("byear"), contactData.getYearOfBirthday());
        select(By.name("aday"), contactData.getDayOfAnniversary());
        select(By.name("amonth"), contactData.getMonthOfAnniversary());
        type(By.name("ayear"), contactData.getYearOfAnniversary());

        if(creation)
        {
            if(contactData.getGroupName() == null)
            {
                select(By.name("new_group"), "[none]");
            }
            else
            {
                select(By.name("new_group"), contactData.getGroupName());
            }
        }
        else
        {
            Assert.assertFalse(isElementPresent(By.name("new group")));
        }
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNotes());
    }

    public void submitContactCreation()
    {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initContactModificationById(int id)
    {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))). click();
    }

    public void submitContactModification()
    {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void selectAllContacts()
    {
        click(By.id("MassCB"));
    }

    public void deleteSelectedContacts()
    {
        click(By.xpath("//input[@value='Delete']"));
    }

    public boolean isThereAContact()
    {
        return isElementPresent(By.name("selected[]"));
    }

    public ContactData infoFromEditForm(ContactData cd)
    {
        initContactModificationById(cd.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();

        return new ContactData().withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }
    private void selectGroup(int index) {
        wd.findElement(By.cssSelector("select[name='to_group'] option[value='" + index + "'")).click();
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectById(contact.getId());
        selectGroupToAddContact(group);
        addContactToGroup();
    }


    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    private void selectGroupToAddContact( GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    }

    private void addContactToGroup() {
        click(By.xpath("//input[@value='Add to']"));
    }

}
