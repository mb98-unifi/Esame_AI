package it.unifi;

public class LineInterceptor {
    private Matrix matrix;
    private Point[] points;

    public LineInterceptor(Matrix matrix, Point[] points) {
        this.matrix = matrix;
        this.points = points;
    }

    public boolean isIntercepted(int k, int s) {
        Point p1 = points[k];
        Point p2 = points[s];
        double m, q, m1, q1, x, y;
        //Caso x1 e x2 allineati

        if (p1.getX() == p2.getX()) {
            for (int i = 0; i < points.length; i++) {
                for (int j = i; j < points.length; j++) {
                    if (matrix.hasEdge(i, j) && points[i].getX() != points[j].getX()) {
                        m1 = (points[j].getY() - points[i].getY()) / (points[j].getX() - points[i].getX());
                        q1 = (points[j].getX() * points[i].getY() - points[i].getX() * points[j].getY()) / (points[j].getX() - points[i].getX());
                        y = m1 * p1.getX() + q1;
                        if (p1.getX() > Math.min(points[i].getX() + 0.01, points[j].getX() + 0.01) && p2.getX() < Math.max(points[i].getX() - 0.01, points[j].getX() - 0.01) && y > Math.min(p1.getY(), p2.getY()) && y < Math.max(p1.getY(), p2.getY())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        //Caso generico
        m = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        q = (p2.getX() * p1.getY() - p1.getX() * p2.getY()) / (p2.getX() - p1.getX());

        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {

                if (matrix.hasEdge(i, j) && points[i].getX() != points[j].getX()) {
                    m1 = (points[j].getY() - points[i].getY()) / (points[j].getX() - points[i].getX());
                    q1 = (points[j].getX() * points[i].getY() - points[i].getX() * points[j].getY()) / (points[j].getX() - points[i].getX());
                    x = (q1 - q) / (m - m1);
                    if (x > Math.min(points[i].getX() + 0.01, points[j].getX() + 0.01) && x < Math.max(points[i].getX() - 0.01, points[j].getX() - 0.01) && x > Math.min(p1.getX() + 0.01, p2.getX() + 0.01) && x < Math.max(p1.getX() - 0.01, p2.getX() - 0.01)) {
                        return true;
                    }
                } else if (matrix.hasEdge(i, j) && points[i].getX() == points[j].getX()) {
                    y = m * points[i].getX() + q;
                    if (points[i].getX() > Math.min(p1.getX(), p2.getX()) && points[i].getX() < Math.max(p1.getX(), p2.getX()) && y > Math.min(points[i].getY(), points[j].getY()) && y < Math.max(points[i].getY(), points[j].getY())) {
                        return true;
                    }
                }

            }
        }


        return false;
    }


}
