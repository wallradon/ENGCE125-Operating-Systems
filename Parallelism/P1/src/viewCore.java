
import java.util.concurrent.*;

public class viewCore {

    public void core() {
        int size = 100;
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores + " Cores");
        int chunkSize = size / cores;
        System.out.println(chunkSize);
    }

    public void executorSv(int[] data) {
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
}
