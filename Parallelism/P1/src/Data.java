
import java.util.Arrays;

public class Data {

    public static void main(String[] args) {
        //create Data
        int size = 10_000;
        int[] numbers1 = new int[size]; //reference type
        int[] numbers2 = new int[size]; //reference type
        for (int i = 0; i < size; i++) {
            numbers1[i] = (int) (Math.random() * size);
            numbers2[i] = numbers1[i] ;
        }//end for

        QuickSort sort = new QuickSort();
        viewCore V = new viewCore();
        long startTime;
        long endTime;
        long duration;
        
        System.out.println("singel");
        System.out.println("Original Array: " + Arrays.toString(numbers1));
        startTime = System.nanoTime();

        sort.quickSort(numbers1, 0, numbers1.length - 1);

        endTime = System.nanoTime();
        duration = endTime - startTime;
        
        System.out.println("Sorted Array: " + Arrays.toString(numbers1));
        System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");
        //---------------------------------------------------------------------------------------//
        System.out.println("//---------------------------------------------------------------------------------------//");
        System.out.println("MUX");
        System.out.println("Original Array: " + Arrays.toString(numbers2));
        startTime = 0;
        endTime = 0;
        startTime = System.nanoTime();
        
        V.executorSv(numbers2);

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("Sorted Array: " + Arrays.toString(numbers2));
        System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");

    }

}
