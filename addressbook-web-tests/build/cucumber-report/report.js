$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("bdd/groups.feature");
formatter.feature({
  "line": 1,
  "name": "Groups",
  "description": "",
  "id": "groups",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group whith name \u003cname\u003e, header \u003cheader\u003e and footer \u003cfooter\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with the added group",
  "keyword": "Then "
});
formatter.examples({
  "line": 8,
  "name": "",
  "description": "",
  "id": "groups;group-creation;",
  "rows": [
    {
      "cells": [
        "name",
        "footer",
        "header"
      ],
      "line": 9,
      "id": "groups;group-creation;;1"
    },
    {
      "cells": [
        "test name",
        "test footer",
        "test header"
      ],
      "line": 10,
      "id": "groups;group-creation;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 16578942891,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group whith name test name, header test header and footer test footer",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with the added group",
  "keyword": "Then "
});
formatter.match({
  "location": "GroupStepDefinitions.loadGroups()"
});
formatter.result({
  "duration": 477899910,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test name",
      "offset": 32
    },
    {
      "val": "test header",
      "offset": 50
    },
    {
      "val": "test footer",
      "offset": 73
    }
  ],
  "location": "GroupStepDefinitions.createGroups(String,String,String)"
});
formatter.result({
  "duration": 3941872345,
  "status": "passed"
});
formatter.match({
  "location": "GroupStepDefinitions.verifyGroupCreated()"
});
formatter.result({
  "duration": 75176059,
  "status": "passed"
});
formatter.after({
  "duration": 1257411831,
  "status": "passed"
});
});