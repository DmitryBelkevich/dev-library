package com.hard;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int k = 5;
        int from = 1;
        int to = 10;

        // Умножение матрицы на число
//        int[][] matrix = Arrays.init(n, m, from, to);
//        int x = 2;
//        int[][] result = Arrays.init(n, m);
//
//        System.out.println("Matrix:");
//        Arrays.print(matrix);
//
//        System.out.println("Number:");
//        System.out.println(x);
//
//        Matrix.multipliedByNumber(matrix, x, result);
//
//        System.out.println("Result:");
//        Arrays.print(result);

        // Умножение матрицы на матрицу столбец
//        int[][] matrix = Arrays.init(n, m, from, to);
//        int[] vector = Arrays.init(m, from, to);
//        int[] result = Arrays.init(n);
//
//        System.out.println("Matrix:");
//        Arrays.print(matrix);
//
//        System.out.println("Vector:");
//        Arrays.print(vector);
//
//        Matrix.multipliedByVector(matrix, vector, result);
//
//        System.out.println("Result:");
//        Arrays.print(result);

        // Умножение матриц
//        int[][] matrix1 = Arrays.init(n, m, from, to);
//        int[][] matrix2 = Arrays.init(m, k, from, to);
//        int[][] result = Arrays.init(n, k);
//
//        System.out.println("Matrix A:");
//        Arrays.print(matrix1);
//
//        System.out.println("Matrix B:");
//        Arrays.print(matrix2);
//
//        Matrix.multipliedByMatrix(matrix1, matrix2, result);
//
//        System.out.println("Result:");
//        Arrays.print(result);

        // Транспонирование матрицы
//        int[][] matrix = Arrays.init(n, m, from, to);
//        int[][] result = Arrays.init(m, n);
//
//        System.out.println("Matrix:");
//        Arrays.print(matrix);
//
//        Matrix.transposition(matrix, result);
//
//        System.out.println("Result:");
//        Arrays.print(result);
    }
}

class Matrix {
    public static void multipliedByNumber(int[][] matrix, int x, int[][] result) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                result[i][j] = matrix[i][j] * x;
    }

    public static void multipliedByVector(int[][] matrix, int[] vector, int[] result) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                result[i] += matrix[i][j] * vector[j];
    }

    public static void multipliedByMatrix(int[][] matrix1, int[][] matrix2, int[][] result) {
        for (int i = 0; i < matrix1.length; i++)
            for (int j = 0; j < matrix2[i].length; j++)
                for (int k = 0; k < matrix2.length; k++)
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
    }

    public static void transposition(int[][] matrix, int[][] result) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                result[j][i] = matrix[i][j];
    }
}

class Arrays {
    public static int[] init(int n) {
        int[] arr = new int[n];

        return arr;
    }

    public static int[][] init(int n, int m) {
        int[][] arr = new int[n][m];

        return arr;
    }

    public static int[] init(int n, int from, int to) {
        int arr[] = new int[n];

        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * (to - from + 1) + from);

        return arr;
    }

    public static int[][] init(int n, int m, int from, int to) {
        int[][] matrix = new int[n][];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[m];

            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = (int) (Math.random() * (to - from + 1) + from);
        }

        return matrix;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.println(
                    arr[i]
                    //+ " "
            );
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + " ");

            System.out.println();
        }
    }
}
