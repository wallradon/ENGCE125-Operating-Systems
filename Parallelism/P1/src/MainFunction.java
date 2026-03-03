import java.util.Arrays;

public class MainFunction {

    public static void main(String[] args) {
        //create Data
        int size = 100_000;
        int round = 5;
        int numbers1[];
        int numbers2[];

        viewCore V = new viewCore();
        Sequential seq = new Sequential();
        long startTime;
        long endTime;
        long duration;
        long totaltimeDataSequential = 0;
        long totaltimeTaskSequential = 0;
        long totaltimeDatap = 0;
        long totaltimeTaskP = 0;

        //Task Sequential
        for (int i = 0; i < round; i++) {
            System.out.println("Task Sequential");
            numbers1 = random(size);
//            System.out.println("Normal Array: " + Arrays.toString(numbers1));
            startTime = System.nanoTime(); //START
            seq.sortSequentialAndStats(numbers1);
            endTime = System.nanoTime(); //STOP
            duration = endTime - startTime;
            totaltimeTaskSequential += duration;
//            System.out.println("Sorted Array: " + Arrays.toString(numbers1));
            System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");
            System.out.println("");

            //Task parallelism
            System.out.println("Task parallelism");
            numbers2 = numbers1;
            startTime = System.nanoTime(); //START
            V.taskParallelism(numbers2);
            endTime = System.nanoTime(); //STOP
            duration = endTime - startTime;
            System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");
            totaltimeTaskP += duration;
            System.out.println("");

        }

        long avgTseq = totaltimeTaskSequential / round;
        long avgTPara = totaltimeTaskP / round;
        System.out.println("avgTseq : " + (avgTseq / 1_000_000.0) + " ms");
        System.out.println("avgTPara : " + (avgTPara / 1_000_000.0) + " ms");
        System.out.println("//-------------------------------------------------------------------------------------------------------//");
        //Sequential Data
        for (int i = 0; i < round; i++) {
            System.out.println("Sequential Data");
            numbers1 = random(size);
//            System.out.println("Normal Array: " + Arrays.toString(numbers1));
            startTime = System.nanoTime(); //START
            seq.sortData(numbers1);
            endTime = System.nanoTime(); //STOP
            duration = endTime - startTime;
            totaltimeDataSequential += duration;
//            System.out.println("Sorted Array: " + Arrays.toString(numbers1));
            System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");
            System.out.println("");

            System.out.println("Data parallelism");
            //Data parallelism

            numbers2 = numbers1;
//            System.out.println("Original Array: " + Arrays.toString(numbers2));
            startTime = System.nanoTime(); //START
            V.dataParallelism(numbers2);
            endTime = System.nanoTime(); //STOP
            duration = endTime - startTime;
//            System.out.println("Sorted Array: " + Arrays.toString(numbers2));
            System.out.println("Meaning time (milliseconds): " + (duration / 1_000_000.0) + " ms");
            totaltimeDatap += duration;
            System.out.println("");

        }

        long avgDSeq = totaltimeDataSequential / round;
        long avgDPara = totaltimeDatap / round;
        System.out.println("avgDSeq : " + (avgDSeq / 1_000_000.0) + " ms");
        System.out.println("avgDPara : " + (avgDPara / 1_000_000.0) + " ms");

    }

    public static int[] random(int size) {

        int lengthOfDataRandom = 1_000_00;
        int[] numbers1 = new int[size]; //reference type
        for (int i = 0; i < size; i++) {
            numbers1[i] = (int) (Math.random() * lengthOfDataRandom);
        }//end for
        return numbers1;
    }

}