package ru.stqa.pft.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;


import java.util.Set;

public class RestTests {
  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }

  private Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("f2c01c8399d967884390d1e87b46b533", "");
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                    .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                            new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}
