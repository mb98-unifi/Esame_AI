package it.unifi;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static it.unifi.Order.*;

public class Main extends JPanel {

    public static void main(String[] args){
        System.out.println("Inserire il numero di nodi: ");
        Scanner t = new Scanner(System.in);
        int n = t.nextInt();
        int[] color;

        long start = System.nanoTime();
        GraphCreator graphCreator = new GraphCreator(n);
        graphCreator.createGraph(n);
        long end = System.nanoTime();

        System.out.println("N = " + n + ", TimeGraphCreator = " + (end - start) / 1000000);

        start = System.nanoTime();
        Matrix spanMatrix = SpanningTree.span(graphCreator.matrix);
        end = System.nanoTime();

        System.out.println("N = " + n + ", TimeSpanningTree = " + (end - start) / 1000000);

        ArrayList<ArrayList<Integer>> domain = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> in;
        for (int i = 0; i < n; i++) {
            in = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
            domain.add(in);
        }

        start = System.nanoTime();
        try {
            color = tree_CSP_solver(spanMatrix, domain);
        } catch (Exception e) {
            color = null;
        }
        end = System.nanoTime();

        System.out.println("N = " + n + ", TimeTreeCSPSolver = " + (end - start) / 1000000);

        GraphPlotter graphPlotter = new GraphPlotter(graphCreator.matrix, graphCreator.points, spanMatrix, color);
        JFrame frame = new JFrame("Points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(graphPlotter);
        frame.setSize(850, 850);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
