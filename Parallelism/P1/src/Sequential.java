
import java.util.Arrays;

public class Sequential {

    public void sortSequentialAndStats(int[] data) {
        int max = -1;
        int min = 9_999_999;
        int total = 0;
        int avg;
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

        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("AVG = " + avg);

    }
}
