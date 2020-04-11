package it.unifi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class SpanningTree {

    public static Matrix span(Matrix matrix) {
        int n;
        ArrayList<Integer> al_conn = new ArrayList<>();
        Queue<Integer> q = new LinkedList<Integer>();
        Matrix spanMatrix = new Matrix(matrix.size());

        q.add(1);
        while (!q.isEmpty()) {
            n = q.remove();
            for (int i = 0; i < matrix.size(); i++) {
                if (matrix.hasEdge(n, i) && !al_conn.contains(i)) {
                    q.add(i);
                    al_conn.add(i);
                    spanMatrix.addEdge(n, i);
                }
            }
        }
        return spanMatrix;
    }
}
