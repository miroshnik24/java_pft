package ru.stqa.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.addressbook.appmanager.ApplicationManager;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class TestBase {
 // Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

//  @BeforeMethod
//  public void logTestStart(Method m, Object[] p) {
//    logger.info("Start test "+ m.getName()+ " with parameters "+ Arrays.asList(p));
//  }
//
//  @AfterMethod(alwaysRun = true)
//  public void logTestStop(Method m, Object[] p) {
//    logger.info("Stop test "+ m.getName() + " with parameters "+ Arrays.asList(p));
//  }
}
