public class MergeSort {
    private int[] merge(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        int[] res = new int[arr1.length + arr2.length];

        // copy until one array is empty
        while (i < arr1.length && j < arr2.length) {
            res[i + j] = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];
        }

        // copy the rest of items in one of arrays if any
        while (i < arr1.length) res[i+j] = arr1[i];
        while (j < arr2.length) res[i+j] = arr2[j];

        return res;
    }

    public void sort(int[] arr) {
        int len = arr.length;
        int[] aux = sort(arr, 0, len - 1);
    }

    private int[] sort(int[] arr, int a, int b) {
        return null;
    }
}