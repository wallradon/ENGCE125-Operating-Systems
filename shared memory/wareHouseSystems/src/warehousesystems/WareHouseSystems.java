package warehousesystems;

import java.util.LinkedList;

public class WareHouseSystems {

    public static void main(String[] args) {
        LinkedList<Integer> wareHouse = new LinkedList<>();
        final int capacity = 5;
        //Producer
        Thread t1 = new Thread(() -> {
            int producVal = 0;
            while (true) {

                synchronized (wareHouse) {
                    while (wareHouse.size() == capacity) {
                        try { //wait consumer item get out 
                            System.out.println("Ware House is Full");
                            wareHouse.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    producVal++;
                    wareHouse.add(producVal);
                    System.out.println("additem | Produc have " + wareHouse.size() + " Item");
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
                            System.out.println("Consumer 1 : wait produc insert item");
                            wareHouse.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    wareHouse.removeFirst();
                    System.out.println("Consumer 1 :  out of Warehouse Produc have " + wareHouse.size() + " Item");
                    wareHouse.notifyAll(); //say i removed it
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
//        Thread t3 = new Thread(() -> {
//            while (true) {
//                synchronized (wareHouse) { //wait produc insert item
//                    while (wareHouse.isEmpty()) { // if empty wait
//                        try {
//                            System.out.println("Consumer 2 : wait produc insert item");
//                            wareHouse.wait();
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                    wareHouse.removeFirst();
//                    System.out.println("Consumer 2 : out of Warehouse Produc have " + wareHouse.size() + " Item");
//                    wareHouse.notifyAll(); //say i removed it
//                }
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        });

        t1.start();
        t2.start();
//        t3.start();
    }

}
