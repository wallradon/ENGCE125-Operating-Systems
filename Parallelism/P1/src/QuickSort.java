
public class QuickSort {

    public void Sort() {
        System.out.println("Hello");
    }

    public int hoarePartition(int[] A, int l, int r) {
        int p = A[l]; // Pivot is the first character
        int i = l;
        int j = r + 1;

        // corresponds to repeat ... until i >= j
        do {
            // repeat i <- i + 1 until A[i] >= p
            do {
                i++;
            } while (i <= r && A[i] < p); // increment i <= r to prevent Array Index Out of Bounds

            // repeat j <- j - 1 until A[j] <= p
            do {
                j--;
            } while (A[j] > p);

            // swap(A[i], A[j]) repeats every iteration, even the last one when i >= j
            swap(A, i, j);

        } while (i < j); // Repeat as long as i is less than j (opposite of until i >= j)

        // swap(A[i], A[j]) // undo last swap when i >= j
        // undo the last swap that was exceeded
        swap(A, i, j);

        //  swap(A[l], A[j]) // place the Pivot at the correct position (j)
        swap(A, l, j);

        return j;
    }//end hoarePartition

    public void quickSort(int[] A, int l, int r) {
        if (l < r) {
            // Find the split position
            int s = hoarePartition(A, l, r);
            // Recursion on the left side
            quickSort(A, l, s - 1);

            // Right-hand recursion
            quickSort(A, s + 1, r);
        }
    }//end quickSort

    // Swap Helper function
    private void swap(int[] A, int i, int j) {
        // Check array boundaries if index is out of array (prevents edge errors)
        if (i >= 0 && i < A.length && j >= 0 && j < A.length) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }//end swap
//-----------------------------------------------------------------------------------------------//
    public void merge(int[] arr, int l, int m, int r) {
        // Find the size of the two parts to merge
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create a temporary array
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to the temporary array
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }
        // Merge the temporary array back into the main arr array
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

    // Collect any remaining terms (if any)
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }//end merge

}//end class
