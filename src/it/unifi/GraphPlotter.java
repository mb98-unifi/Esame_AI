package it.unifi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GraphPlotter extends JPanel {
    private Matrix matrix;
    private Point[] points;
    private Matrix spanMatrix;
    private int[] color;

    public GraphPlotter(Matrix matrix, Point[] points, Matrix spanMatrix, int[] color) {
        this.matrix = matrix;
        this.points = points;
        this.spanMatrix = spanMatrix;
        this.color = color;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                g2d.setColor(Color.gray);
                g2d.setStroke(new BasicStroke(1));
                if (matrix.hasEdge(i, j)) {
                    g2d.draw(new Line2D.Double(points[i].getX(), points[i].getY(), points[j].getX(), points[j].getY()));
                }

                g2d.setColor(Color.red);
                g2d.setStroke(new BasicStroke(2));

                if (spanMatrix.hasEdge(i, j)) {
                    g2d.draw(new Line2D.Double(points[i].getX(), points[i].getY(), points[j].getX(), points[j].getY()));
                }
            }
        }

        if (color != null) {
            for (int i = 0; i < points.length; i++) {
                if (color[i] == 1) {
                    g2d.setColor(Color.blue);
                } else if (color[i] == 2) {
                    g2d.setColor(Color.green);
                } else if (color[i] == 3) {
                    g2d.setColor(Color.yellow);
                }else if (color[i] == 4) {
                    g2d.setColor(Color.magenta);
                }
                Ellipse2D.Double shape = new Ellipse2D.Double(points[i].getX() - 5, points[i].getY() - 5, 10, 10);
                g2d.fill(shape);
            }
        } else {
            g2d.setColor(Color.gray);
            for (int i = 0; i < points.length; i++) {
                Ellipse2D.Double shape = new Ellipse2D.Double(points[i].getX() - 5, points[i].getY() - 5, 10, 10);
                g2d.fill(shape);
            }
            g2d.setColor(Color.red);
            Font font = new Font("Verdana", Font.BOLD, 60);
            g.setFont(font);
            g2d.drawString("Unsatisfiable", 280, 300);
        }
    }
}
