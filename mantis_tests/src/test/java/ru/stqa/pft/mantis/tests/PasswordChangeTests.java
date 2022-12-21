package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.*;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;



public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange() throws IOException, MessagingException {
    app.passwordChange().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.passwordChange().goToManagePage();
    app.passwordChange().goToManageUsers();
    app.passwordChange().chooseUser();
    String username = app.passwordChange().getUserName();
    String usermail = app.passwordChange().getUserMail();
    app.passwordChange().resetUserPassword();
    List<MailMessage> mailMessage = app.mail().waitForMail(1, 10000);
    String resetLink = findResetLink(mailMessage, usermail);
    app.passwordChange().goToResetPage(resetLink);
    String newPassword = "password";
    app.passwordChange().setNewPassword(newPassword);
    HttpSession session = app.newSession();
    assertTrue(session.login(username, newPassword));
    assertTrue(session.isLoggedInAs(username));
  }

  private String findResetLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
