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

public class ContactHelper extends HelperBase {

    private String group;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

//    public void fillContactForm(ContactData contactData, boolean creation) {
//        type(By.name("firstname"), contactData.getFirstname());
//        type(By.name("middlename"), contactData.getMiddlename());
//        type(By.name("lastname"), contactData.getLastname());
//        type(By.name("address"), contactData.getAddres());
//        type(By.name("home"), contactData.getHomephone());
//        type(By.name("mobile"), contactData.getMobilephone());
//        type(By.name("work"), contactData.getWorkPhone());
//        type(By.name("email"), contactData.getEmail());
//        type(By.name("email2"), contactData.getEmail2());
//        type(By.name("email3"), contactData.getEmail3());
//        attach(By.name("photo"), contactData.getPhoto());
//
//        if (creation) {
//                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(group);
//            } else {
//                Assert.assertFalse(isElementPresent(By.name("new_group")));
//            }
//        }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        //type(By.name("nickname"), contactData.getNick());
        //type(By.name("title"), contactData.getTitle());
        //type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddres());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkPhone());
       // type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
       // type(By.name("homepage"), contactData.getSite());
        //type(By.name("address2"), contactData.getSecondaryAddress());
        type(By.name("phone2"), contactData.getPhone2());
        //type(By.name("notes"), contactData.getNotes());



        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModificationById(int id) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true );
        submitCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false );
        submitModification();
        contactCache = null;
    }

    public void addInGroup(ContactData addedContact, GroupData group) {
        selectContactById(addedContact.getId());
        chooseToGroupById(group.getId());
        addContactInGroup();
    }

    public void deleteFromGroup(ContactData deletedContact, GroupData group) {
        chooseGroupById(group.getId());
        selectContactById(deletedContact.getId());
        removeContactFromGroup();
    }

    private void chooseToGroupById(int id) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
    }

    private void chooseGroupById(int id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.tagName("tr"));
        elements.remove(0);
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String adress = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            ContactData contact = new ContactData(id, firstname, null, lastname, adress, null
                    , null, null, null, null, null, null, null)
                    .withAllPhones(allPhones).withAllEmails(allEmails);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhome = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData(contact.getId(), firstname, null, lastname, address, homePhone, mobilePhome,
                phone2, workPhone, email, email2, email3, null);

//        return new ContactData().withFirstName(firstName).withLastName(lastName)
//                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2).withAddress(address)
//                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void create(ContactData contact, boolean b) {
        //*create();
        fillContactForm(contact, b);
        submitContactCreation();
        contactCache = null;
        goToHomePage();
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupInList(group.getName());
        initAddToGroup();
    }

    private void initAddToGroup() {
        wd.findElement(By.name("add")).click();
    }

    private void selectGroupInList(String groupName) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
    }

    public void deleteContactFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        initDeletingFromGrop();
    }

    private void initDeletingFromGrop() {
        wd.findElement(By.name("remove")).click();
    }
}
