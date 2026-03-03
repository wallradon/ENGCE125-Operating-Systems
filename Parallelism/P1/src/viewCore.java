import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

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
        //Thread process
        for (int i = 0; i < cores; i++) {
            int start = i * chunkSize;
            //if last = size else = (i + 1) * chunkSize
            int end = (i == cores - 1) ? size : (i + 1) * chunkSize;
            executor.submit(() -> {
                sort.quickSort(data, start, end - 1);
            });//end function
        }//end for
        executor.shutdown();
        try {
//            with 1 minuts if 1 minuts do nor done shutdownNow
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        // merge data
        int currentSortedEnd = chunkSize - 1;
        for (int i = 1 ; i < cores ; i++) { //1-11
            // Find the end point of the next chunk to merge Ex
            int nextChunkEnd = (i == cores - 1) ? size - 1 : (i + 1) * chunkSize - 1;
            // Merge: (from the beginning...to the end of the first section) 
            //combined with (first section + 1...to the end of the next section)
            sort.merge(data, 0, currentSortedEnd, nextChunkEnd);
            // Move the end point because the merge is complete
            currentSortedEnd = nextChunkEnd;
        }
    }

    public void taskParallelism(int[] data) {
        AtomicLong max = new AtomicLong(Long.MAX_VALUE);
        AtomicLong min = new AtomicLong(Long.MIN_VALUE);
        AtomicLong avg = new AtomicLong(0);
        AtomicLong mean = new AtomicLong(0);
//        double[] med = new double[1];

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        //MAX
        executor.submit(() -> {
            max.set(taskMax(data));

        });
        //Min
        executor.submit(() -> {
            min.set(taskMin(data));
        });
        //AVG
        executor.submit(() -> {
            avg.set(taskAvg(data));
        });
        //Mean
        executor.submit(() -> {
            mean.set(taskAvg(data));
        });

//        executor.submit(() -> {
//            med[0] = median(data);
//        });

        executor.shutdown();
        try {
//            with 1 minuts
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("max = " + max);
        System.out.println("Min = " + min);
        System.out.println("AVG = " + avg);
        System.out.println("Mean = " + mean);
//        System.out.println("median = " + med[0]);
    }//end taskParallelism

    public long taskMax(int[] data) {
        long max = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }//end for
        return max;
    }

    public long taskMin(int[] data) {
        long min = 9_999_999;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }//end for
        return min;
    }
    
    public long taskAvg(int[] data) {
        long total = 0;
        long avg;
        for (int i = 0; i < data.length; i++) {

            total += data[i];
        }
        avg = total / data.length;
        return avg;
    }

    public double median(int[] data) {
        int size = data.length;
        int mid = size / 2;
        if (size % 2 != 0) {
            // For odd numbers: Pick the middle number (e.g., 5/2 = 2, which is the 3rd index)
            return (double) data[mid];
        } else {
            // For even numbers: Add the two middle numbers together and divide by 2
            return (double) (data[mid - 1] + data[mid]) / 2.0;
        }
    }

}
