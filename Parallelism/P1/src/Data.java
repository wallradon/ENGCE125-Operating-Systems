public class Data {


    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int countData = 10_000 ;
        int[] data = new int[countData];
        sort.Sort();
        for (int i = 0; i < countData; i++) {
            data[i] = (int)(Math.random()*countData) ;
            System.out.println( i +". " + data[i]);
        }
    }
    
}
