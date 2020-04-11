package it.unifi;

public abstract class QuickSort {

    public static int partition(double arr[][], int low, int high) {
        double pivot = arr[0][high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {

            if (arr[0][j] < pivot) {
                i++;

                double temp = arr[0][i];
                arr[0][i] = arr[0][j];
                arr[0][j] = temp;

                double temp_1 = arr[1][i];
                arr[1][i] = arr[1][j];
                arr[1][j] = temp_1;

            }
        }

        double temp = arr[0][i + 1];
        arr[0][i + 1] = arr[0][high];
        arr[0][high] = temp;

        double temp_1 = arr[1][i + 1];
        arr[1][i + 1] = arr[1][high];
        arr[1][high] = temp_1;

        return i + 1;
    }

    public static void sort(double arr[][], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
}
