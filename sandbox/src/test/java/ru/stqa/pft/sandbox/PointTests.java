package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void TestArea1() {
    Point p1 = new Point(10, 5);
    Point p2 = new Point(7, 15);
    Assert.assertEquals(p1.distance(p2), 10.44030650891055);
  }

  @Test
  public void TestArea2() {
    Point p1 = new Point(10, 5);
    Point p2 = new Point(7, 15);
    Assert.assertEquals(p1.distance(p2), -25);
  }


}

