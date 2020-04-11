package it.unifi;

public class Matrix {

        private final boolean[][] matrix;
        int  N;

        public Matrix(int N) {
            this.N = N;
            matrix = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = false;
                }
            }
        }

        public void addEdge(int x, int y) {
            matrix[x][y] = true;
            matrix[y][x] = true;
        }

        public boolean hasEdge(int i, int j) {
            return matrix[i][j];
        }


        public int size(){
            return N;
        }
    }

