package ru.stqa.pft.sandbox;

//import static java.lang.System.*;

public class Point {
  public static double p1;
  private static double p2;

  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();

    Point.p1 = 4;
    Point.p2 = 10;

    System.out.println("Расстояние между точками (" + Point.p1 + ") и (" + Point.p2 + ") = " + distance());
  }

  public static double distance() {
    return Math.sqrt(p2 - p1);
  }


}
