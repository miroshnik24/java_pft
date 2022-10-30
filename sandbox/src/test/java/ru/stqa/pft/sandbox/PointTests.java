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
    Point p1 = new Point(-5, 5);
    Point p2 = new Point(17, 0);
    Assert.assertEquals(p1.distance(p2), 22.561028345356956);
  }

  @Test
  public void TestArea3() {
    Point p1 = new Point(99, -101);
    Point p2 = new Point(100, -99);
    Assert.assertEquals(p1.distance(p2), 2.23606797749979);
  }

}

