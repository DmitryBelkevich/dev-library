package com.hard;

public class Main {
    public static void main(String[] args) {
        int n = 100 * 1000;
        int from = 1;
        int to = n;

        int arr[] = Arrays.init(n, from, to);

        long start = System.currentTimeMillis();

        //Sorting.bubbleSort(arr);			// 12000 ms (12.0 s)
        Sorting.bubbleSort2(arr);            // 12200 ms (12.2 s)
        //Sorting.insertionSort(arr);		// 4500 ms (4.5 s)
        //Sorting.selectionSort(arr);		// 1050 ms (1.05 s)
        //Sorting.cocktailSort(arr);		// 11300 ms (11.3 s)
        //Sorting.shellSort(arr);			// 15 ms (0.015 s)
        //Sorting.quickSort(arr, 0, n - 1);	// 13 ms (0.13 s)

        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        double timeConsumedSeconds = timeConsumedMillis * 0.001;

        Arrays.print(arr);

        System.out.println(
                timeConsumedMillis + " ms"
                        + " (" + timeConsumedSeconds + " s)"
        );
    }
}

class Sorting {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - 1 - i; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void bubbleSort2(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            for (int j = i; j > 0; j--)
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
    }

    public static void selectionSort(int[] arr) {
        // По очереди будем просматривать все подмножества элементов массива
        // (0 - последний, 1-последний, 2-последний, ...)
        for (int i = 0; i < arr.length - 1; i++) {
            // Предполагаем, что первый элемент (в каждом подмножестве элементов)
            // является минимальным
            int min = arr[i];
            int min_i = i;

            // В оставшейся части подмножества ищем элемент,
            // который меньше предположенного минимума
            for (int j = i + 1; j < arr.length; j++)
                // Если находим, запоминаем его индекс
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }

            // Если нашелся элемент, меньший, чем на текущей позиции,
            // меняем их местами
            if (i != min_i) {
                int temp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = temp;
            }
        }
    }

    // shakerSort, cocktailSort
    public static void cocktailSort(int[] arr) {
        int left = 0;    // левая граница
        int right = arr.length - 1;    // правая граница

        while (left <= right) {
            // Сдвигаем к концу массива "тяжелые элементы"
            for (int i = left; i < right; i++)
                if (arr[i] > arr[i + 1]) {
                    arr[i] ^= arr[i + 1];
                    arr[i + 1] ^= arr[i];
                    arr[i] ^= arr[i + 1];
                }

            right--; // уменьшаем правую границу

            //Сдвигаем к началу массива "легкие элементы"
            for (int i = right; i > left; i--)
                if (arr[i] < arr[i - 1]) {
                    arr[i] ^= arr[i - 1];
                    arr[i - 1] ^= arr[i];
                    arr[i] ^= arr[i - 1];
                }

            left++;    // увеличиваем левую границу
        }
    }

    public static void shellSort(int[] arr) {
        int i, j, k, h, m = 0, b = arr.length;
        int[] d = {
                1, 4, 10, 23, 57,
                145, 356, 911, 1968, 4711,
                11969, 27901, 84801, 213331, 543749,
                1355339, 3501671, 8810089, 21521774, 58548857,
                157840433, 410151271, 1131376761, 2147483647,
        };

        while (d[m] < b)
            ++m;

        while (--m >= 0) {
            k = d[m];

            for (i = k; i < b; i++) {    // k-сортировка
                j = i;
                h = arr[i];

                while ((j >= k) && (arr[j - k] > h)) {
                    arr[j] = arr[j - k];
                    j -= k;
                }

                arr[j] = h;
            }
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        int i = low;
        int j = high;
        int p = arr[(low + high) / 2];

        // Partition
        while (i <= j) {
            while (arr[i] < p)
                ++i;

            while (arr[j] > p)
                --j;

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
        }

        // Recursion
        if (low < j)
            quickSort(arr, low, j);
        if (i < high)
            quickSort(arr, i, high);
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
