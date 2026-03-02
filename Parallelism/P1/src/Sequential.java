
import java.util.Arrays;

public class Sequential {

    public void sortSequentialAndStats(int[] data) {
        long max = -1;
        long min = 9_999_999;
        long total = 0;
        long avg;
        double median = 0 ;
        Arrays.sort(data);
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        for (int i = 0; i < data.length; i++) {

            if (data[i] < min) {
                min = data[i];
            }

        }
        for (int i = 0; i < data.length; i++) {

            total += data[i];
        }
        avg = total / data.length;

        for (int i = 0; i < data.length; i++) {
            int size = data.length;
            int mid = size / 2;
            if (size % 2 != 0) {
                // For odd numbers: Pick the middle number (e.g., 5/2 = 2, which is the 3rd index)
                median =  data[mid];
            } else {
                // For even numbers: Add the two middle numbers together and divide by 2
                median = (data[mid - 1] + data[mid]) / 2.0;
            }
        }

        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("AVG = " + avg);
        System.out.println("mean = " + avg);
//        System.out.println("median = " + median) ;

    }
}
