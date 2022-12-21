package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase
{
  private Groups groupCache = null;

  public GroupHelper(WebDriver wd)
  {
    super(wd);
  }

  public void submitGroupCreation()
  {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData)
  {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation()
  {
    click(By.name("new"));
  }

  public void deleteSelectedGroups()
  {
    click(By.name("delete"));
  }

  public void selectGroupById(int id)
  {
    wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
  }

  public void initGroupModification()
  {
    click(By.name("edit"));
  }

  public void submitGroupModification()
  {
    click(By.name("update"));
  }

  public void returnToGroupPage()
  {
    click(By.linkText("group page"));
  }

  public void create(GroupData group)
  {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group)
  {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup()
  {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereAGroupByName(String name)
  {
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    if(elements.size() > 0)
    {
      for(WebElement e : elements)
      {
        String text = e.getText();

        if(name.equals(text))
        {
          return true;
        }
      }
    }

    return false;
  }

  public int count()
  {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Groups all()
  {
    if(groupCache != null)
    {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for(WebElement e : elements)
    {
      String name = e.getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

  public void delete(GroupData group)
  {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }
}