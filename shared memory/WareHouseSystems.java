package warehousesystems;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class WareHouseSystems {

    public static void main(String[] args) {
        LinkedList<Integer> wareHouse = new LinkedList<>();
        AtomicInteger produc = new AtomicInteger(0);
        final int capacity = 5 ;
        //Producer
        Thread t1 = new Thread(() -> {
            while (true) {


                synchronized (wareHouse) {
                    while(wareHouse.size() == capacity ){
                        try { //wait consumer item get out 
                            System.out.println("Ware House is Full") ;
                            wareHouse.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int producVal = produc.incrementAndGet(); //producVal++
                    wareHouse.add(producVal);
                    System.out.println("additem : " + producVal+" Produc have " + wareHouse.size()+" Item");
                    wareHouse.notifyAll(); //is not empty wakeUp
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

//      Consumer
        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (wareHouse) { //wait produc insert item
                    while (wareHouse.isEmpty()) { // if empty wait
                        try {
                            System.out.println("wait produc insert item");
                            wareHouse.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int Consumeritem = wareHouse.removeFirst();
                    System.out.println("out of Warehouse: " + Consumeritem + " Produc have " + wareHouse.size()+" Item");
                    wareHouse.notifyAll(); //say i removed it
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        });
        t1.start();
        t2.start();
    }

}
