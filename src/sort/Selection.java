package sort;

public class Selection {

    static int[] selectionSort(int[] arr) {
        // find the smallest elemnt in the array and swap it
        int length = arr.length;
        // for the number of iterations
        for (int i = 0; i < length; i++) {
            int min_index = i;
            // for checking the smallest element in array
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }

            // swap the smallest element to its actual position
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    static void main() {
        int[] arr = {4, 2, 6, 1, 8, 3};
        int[] arr1 = selectionSort(arr);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
    }
}
