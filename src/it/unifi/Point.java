package it.unifi;

public class Point {
    private int x;
    private int y;

    public Point() {
        this.x = (int) Math.floor(Math.random() * 800);
        this.y = (int) Math.floor(Math.random() * 800);
    }

    public double distance(Point p) {
        return Math.sqrt((p.getY() - y) * (p.getY() - y) + (p.getX() - x) * (p.getX() - x));
    }


    public double[] distanceFromAll(Point[] p) {
        double[][] distances = new double[2][p.length];
        for (int i = 0; i < p.length; i++) {
            distances[0][i] = this.distance(p[i]);
            distances[1][i] = i;
        }

        QuickSort.sort(distances, 0, p.length - 1);
        return distances[1];
    }


    //getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
