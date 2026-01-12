package warehousesystems;

import java.util.LinkedList;

public class thradClass {

    public void Producer() {
        LinkedList<Integer> wareHouse = new LinkedList<>();
        final int capacity = 5;
        int min = 100;
        int max = 1000;
        
        Thread t1 = new Thread(() -> {

            int producVal = 0;
            while (true) {
                int randomNum1 = (int) (Math.random() * (max - min + 1)) + min;
                synchronized (wareHouse) {
                    while (wareHouse.size() == capacity) {
                        try { //wait consumer item get out 
                            System.out.println("Ware House is Full");
                            System.out.println("----------------------------------------------------");
                            wareHouse.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    producVal++;
                    wareHouse.add(producVal);
                    System.out.println("additem | Produc have " + wareHouse.size() + " Item");
                    System.out.println("----------------------------------------------------");
                    wareHouse.notifyAll(); //is not empty wakeUp

                }
                try {
                    Thread.sleep(randomNum1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        
    }
}
