
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class viewCore {

    public void core() {
        int size = 100;
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores + " Cores");
        int chunkSize = size / cores;
        System.out.println(chunkSize);
    }

    public void dataParallelism(int[] data) {
        int size = data.length;
        QuickSort sort = new QuickSort();

        int cores = Runtime.getRuntime().availableProcessors(); // chack cores
        ExecutorService executor = Executors.newFixedThreadPool(cores); // createThread

        int chunkSize = size / cores;
        for (int i = 0; i < cores; i++) {
            int start = i * chunkSize;
            int end = (i == cores - 1) ? size : (i + 1) * chunkSize;

            executor.submit(() -> {
                sort.quickSort(data, start, end - 1);
            });//end function
        }//end for

        executor.shutdown();
        try {
//            with 1 minuts
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        // merge data
        int currentSortedEnd = chunkSize - 1;

        for (int i = 1; i < cores; i++) {
            // Find the end point of the next chunk to merge
            int nextChunkEnd = (i == cores - 1) ? size - 1 : (i + 1) * chunkSize - 1;

            // Merge: (from the beginning...to the end of the first section) 
            //combined with (first section + 1...to the end of the next section)
            sort.merge(data, 0, currentSortedEnd, nextChunkEnd);

            // Move the end point because the merge is complete
            currentSortedEnd = nextChunkEnd;
        }
    }

    public void taskParallelism(int[] data) {
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger avg = new AtomicInteger(0);
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);
        for (int i = 0; i < cores; i++) {
            //MAX
            executor.submit(() -> {
                max.set(taskMax(data));
                
            });
            //Min
            executor.submit(() -> {
                min.set(taskMin(data));
            });
            executor.submit(() -> {
                avg.set(taskAvg(data));
            });
        }//end for
        executor.shutdown();
        System.out.println("max = " + max);
        System.out.println("Min = " + min);
        System.out.println("AVG = " + avg);
        try {
//            with 1 minuts
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }//end taskParallelism

    public int taskMax(int[] data) {
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }//end for
        return max;
    }

    public int taskMin(int[] data) {
        int min = 9_999_999;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }//end for
        return min;

    }

    public int taskAvg(int[] data) {
        int total = 0;
        int avg;
        for (int i = 0; i < data.length; i++) {

            total += data[i];
        }
        avg = total / data.length;
        return avg;

    }
}
