package ru.stqa.pft.sandbox;

public class Pointdate {

  public static void main(String[] args) {
    Point p1 = new Point(1,2);
    Point p2 = new Point(4,3);
    System.out.println("Расстояние между точками = "+p1.distance(p2));
  }

}

class Point{
  private final double x;
  private final double y;

  public Point(double x1, double y1) {
    this.x = x1;
    this.y = y1;
  }

  public double distance(Point p2){
    return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
  }

}