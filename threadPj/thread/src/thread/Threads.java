//package thread;
//
//import java.util.Scanner;
//
//public class Threads {
//
//    public static volatile String Text = "";
//
//    public static void main(String[] args) {
//        SearchALG SALG = new SearchALG();
//        Scanner sc = new Scanner(System.in);
//
//        String[] word = {"Dog", "Cat", "Tiger"};
//
//        Thread t1 = new Thread(() -> {
//
//            while (true) {
//                System.out.println("Enter Text : ");
//                Text = sc.nextLine();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        });
//        Thread t2 = new Thread(() -> {
//
//            while (true) {
////                System.out.println("2");
//                SALG.Search(word, Text);
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        });
////        Thread t3 = new Thread(() -> {
////            while (true) {
////                System.out.println("3");
////                try {
////                    Thread.sleep(100);
////                } catch (InterruptedException e) {
////                    Thread.currentThread().interrupt();
////                }
////            }
////        });
//        t1.start();
//        t2.start();
////        t3.start();
//
//    }
//
//}
