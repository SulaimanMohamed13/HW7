/******************************************************************
 *
 *   Sulaiman Mohamed / 272 001
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values        - int[] array to be sorted.
     * @param ascending     - if true,method performs an ascending sort, else descending.
     *                        There are two method signatures allowing this parameter
     *                        to not be passed and defaulting to 'true (or ascending sort).
     */
    public void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending) {
        int n = values.length;

        for (int i = 0; i < n - 1; i++) {
            int extremeIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (ascending) {
                    if (values[j] < values[extremeIndex]) {
                        extremeIndex = j;
                    }
                } else {
                    if (values[j] > values[extremeIndex]) {
                        extremeIndex = j;
                    }
                }
            }
            // Swap the found extreme element with the first element
            int temp = values[extremeIndex];
            values[extremeIndex] = values[i];
            values[i] = temp;
        }
    }

    /**
     * Method mergeSortDivisibleByKFirst
     *
     * This method will perform a merge sort algorithm. However, all numbers
     * that are divisible by the argument 'k', are returned first in the sorted
     * list.
     */
    public void mergeSortDivisibleByKFirst(int[] values, int k) {
        if (k == 0) return;
        if (values.length <= 1) return;
        mergeSortDivisibleByKFirst(values, k, 0, values.length - 1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisibleByKFirst(values, k, left, mid, right);
    }

    private void mergeDivisibleByKFirst(int[] arr, int k, int left, int mid, int right) {
        // Create temporary arrays
        int[] leftArray = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArray = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, kIdx = left;

        // Merge left and right arrays with divisible by k first
        while (i < leftArray.length && j < rightArray.length) {
            boolean leftDiv = (leftArray[i] % k == 0);
            boolean rightDiv = (rightArray[j] % k == 0);

            if (leftDiv && !rightDiv) {
                arr[kIdx++] = leftArray[i++];
            } else if (!leftDiv && rightDiv) {
                arr[kIdx++] = rightArray[j++];
            } else {
                // Both divisible or both not divisible - compare values
                if (leftArray[i] <= rightArray[j]) {
                    arr[kIdx++] = leftArray[i++];
                } else {
                    arr[kIdx++] = rightArray[j++];
                }
            }
        }

        // Copy remaining elements
        while (i < leftArray.length) {
            arr[kIdx++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            arr[kIdx++] = rightArray[j++];
        }
    }

    /**
     * Method asteroidsDestroyed
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     */
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long currentMass = mass; // Use long to prevent integer overflow
        
        for (int asteroid : asteroids) {
            if (currentMass < asteroid) {
                return false;
            }
            currentMass += asteroid;
        }
        return true;
    }

    /**
     * Method numRescueSleds
     *
     * Return the minimum number of rescue sleds required to hold all people
     */
    public static int numRescueSleds(int[] people, int limit) {
        Arrays.sort(people);
        int sleds = 0;
        int left = 0;
        int right = people.length - 1;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            sleds++;
        }
        return sleds;
    }
}
