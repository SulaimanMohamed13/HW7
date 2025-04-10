/******************************************************************
 *
 *   YOUR NAME / SECTION NUMBER
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Selection Sort implementation with optional ascending/descending order
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
            // Swap the found extreme element
            int temp = values[extremeIndex];
            values[extremeIndex] = values[i];
            values[i] = temp;
        }
    }

    /**
     * Merge Sort with numbers divisible by k first
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

        // First process all divisible elements from both halves
        while (i < leftArray.length && leftArray[i] % k == 0) {
            arr[kIdx++] = leftArray[i++];
        }
        while (j < rightArray.length && rightArray[j] % k == 0) {
            arr[kIdx++] = rightArray[j++];
        }

        // Then process remaining elements from left array
        while (i < leftArray.length) {
            arr[kIdx++] = leftArray[i++];
        }
        
        // Finally process remaining elements from right array
        while (j < rightArray.length) {
            arr[kIdx++] = rightArray[j++];
        }
    }

    /**
     * Asteroids Destroyed problem solution
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
     * Rescue Sleds problem solution
     */
    public static int numRescueSleds(int[] people, int limit) {
        Arrays.sort(people);
        int sleds = 0;
        int left = 0;
        int right = people.length - 1;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++; // Pair the lightest with the heaviest
            }
            right--;
            sleds++;
        }
        return sleds;
    }
}
