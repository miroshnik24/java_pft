package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase
{
  //Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception
  {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception
  {
    app.stop();
  }

//  @BeforeMethod(alwaysRun = true)
//  public void logTestStart(Method m, Object[] p)
//  {
//    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
//  }
//
//  @AfterMethod(alwaysRun = true)
//  public void logTestStop(Method m)
//  {
//    logger.info("Stop test " + m.getName());
//  }

  public void verifyGroupListInUI()
  {
    if(Boolean.getBoolean("verifyUI"))
    {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();

      assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

//  public void verifyContactListInUI()
//  {
//    if(Boolean.getBoolean("verifyUI"))
//    {
//      Contacts dbContacts = app.db().contacts();
//      Contacts uiContacts = app.contact().all();
//
//      Set<ContactData> dbContactsAfter = dbContacts.stream().map((c) -> new ContactData().withId(c.getId()).withLastName(c.getLastName())
//                      .withFirstName(c.getFirstName()).withAddress(c.getAddress()).withAllPhones(c.mergedPhone()).withAllEmails(c.mergeEmails()))
//              .collect(Collectors.toSet());
//      assertThat(uiContacts, equalTo(dbContactsAfter));
//    }
  }
//}