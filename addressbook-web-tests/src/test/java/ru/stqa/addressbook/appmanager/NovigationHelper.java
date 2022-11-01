package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NovigationHelper {
  private FirefoxDriver wd;
  protected GroupHelper groupHelper;

  public NovigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
    groupHelper.wd.findElement(By.linkText("groups")).click();
  }
}
