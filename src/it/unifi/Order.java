package it.unifi;

import java.util.ArrayList;


public abstract class Order {

    public static int[] tree_CSP_solver(Matrix matrix, ArrayList<ArrayList<Integer>>domain) throws Exception {
        ArrayList<Integer> ass = new ArrayList<>();
        ArrayList<Integer> order = topological_sort(matrix, 1);
        int j, k;

        //System.out.println(order);

        DAC(matrix, domain, order);

        for (int i = 0; i < matrix.size(); i++) {
            if(domain.get(i).size() == 0){
                System.out.println("Unsat_DomainDownToZero");
                throw new Exception();
            }
        }

        ass.add(domain.get(order.get(0)).get(0));
        for (int i = 1; i < matrix.size(); i++) {
            int s = domain.get(order.get(i)).size();
            for (k = i; k > 0; k--) {
                if(matrix.hasEdge(order.get(i), order.get(k))){
                    break;
                }
            }
            for (j = 0; j < s; j++) {
                if(domain.get(order.get(i)).get(j) != ass.get(k)){
                    ass.add(domain.get(order.get(i)).get(j));
                    break;
                }
            }
            if(j == s){
                System.out.println("Unsat");
                throw new Exception();
            }
        }
        //System.out.println(ass);


        int[] color = new int[matrix.size()];
        for (int i = 0; i < matrix.size(); i++) {
            color[order.get(i)] = ass.get(i);
        }

        return color;
    }

    public static ArrayList<Integer> topological_sort(Matrix matrix, int root) {
        ArrayList<Integer> order = new ArrayList<>();
        visit(matrix, root, order);
        return order;
    }

    public static void visit(Matrix matrix, int node, ArrayList<Integer> order) {
        order.add(node);
        for (int i = 0; i < matrix.size(); i++) {
            if (matrix.hasEdge(node, i) && !order.contains(i)) {
                visit(matrix, i, order);
            }
        }
    }

    public static void DAC(Matrix matrix, ArrayList<ArrayList<Integer>> domain, ArrayList<Integer> order){
        for (int i = matrix.size() - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0 ; j--) {
                if(matrix.hasEdge(order.get(j), order.get(i))){
                    Revise(order.get(j), order.get(i), domain);
                }
            }

        }
        //System.out.println(domain);
    }

    public static void Revise(int j, int i, ArrayList<ArrayList<Integer>> domain){
        if(domain.get(i).size() == 1){
            domain.get(j).remove(domain.get(i).get(0));
        }
    }
}
