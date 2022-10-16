package ru.stqa.pft.sandbox;

public class Point {
  public static void main (String[] args) {
    Pointdate P = new Pointdate(5,3,2,-7);
    System.out.println("Расстояние равно " + distance (P));
  }

  public static double distance (Pointdate P) {
    return Math.sqrt((P.p3 - P.p1)*(P.p3 - P.p1) + (P.p4 - P.p2) * (P.p4 - P.p2));
  }
}
