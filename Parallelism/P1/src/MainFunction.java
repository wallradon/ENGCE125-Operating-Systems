
import java.util.Arrays;

public class MainFunction {

    public static void main(String[] args) {
        //create Data
        int size = 1000;
        int[] numbers1 = new int[size]; //reference type
        int[] numbers2 = new int[size]; //reference type
        for (int i = 0; i < size; i++) {
            numbers1[i] = (int) (Math.random() * size);
            numbers2[i] = numbers1[i] ;
        }//end for

//      QuickSort sort = new QuickSort();
        viewCore V = new viewCore();
        Sequential seq = new Sequential();
        long startTime;
        long endTime;
        long duration;
        
        System.out.println("Normal Array: " + Arrays.toString(numbers1));
        startTime = System.nanoTime(); //START
        seq.sortSequentialAndStats(numbers1);
        endTime = System.nanoTime(); //STOP
        duration = endTime - startTime;
        System.out.println("Sorted Array: " + Arrays.toString(numbers1));
        System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");
        
        System.out.println("//---------------------------------------------------------------------------------------//");
        startTime = 0; //START
        endTime = 0; //STOP
        System.out.println("Original Array: " + Arrays.toString(numbers2));
        startTime = System.nanoTime(); //START
        V.dataParallelism(numbers2);
        endTime = System.nanoTime(); //STOP
        duration = endTime - startTime;
        System.out.println("Sorted Array: " + Arrays.toString(numbers2));
        System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");

        System.out.println("//---------------------------------------------------------------------------------------//");
        startTime = 0; //START
        endTime = 0; //STOP
        startTime = System.nanoTime(); //START
        V.taskParallelism(numbers2);
        endTime = System.nanoTime(); //STOP
        duration = endTime - startTime;
        System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");
        
    }

}
