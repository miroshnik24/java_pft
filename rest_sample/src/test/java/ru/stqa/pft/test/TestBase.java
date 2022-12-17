package ru.stqa.pft.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

public class TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("f2c01c8399d967884390d1e87b46b533", "");
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId){
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    int state = issues.getAsJsonArray().iterator().next().getAsJsonObject().get("state").getAsInt();
    if (state == 3) // resolved
      return false;
    else
      return true;
  }

}
