package it.unifi;

import java.util.ArrayList;

public class GraphCreator {
    public Matrix matrix;
    public Point[] points;
    public LineInterceptor l;

    public GraphCreator(int n){
        matrix = new Matrix(n);
        points = new Point[n];
        l = new LineInterceptor(matrix, points);
        for (int i = 0; i < n; i++) {
            points[i] = new Point();
        }
    }

    public void createGraph(int n){
        //Calcola  distanza
        ArrayList<double[]> d = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            d.add(points[i].distanceFromAll(points));
        }

        //Crea grafo
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if(!matrix.hasEdge(i, (int)d.get(i)[j]) && !l.isIntercepted(i, (int)d.get(i)[j]) ) {
                    matrix.addEdge(i, (int)d.get(i)[j]);
                }
            }
        }
    }
}

