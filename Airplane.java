import java.lang.Thread;
import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Airplane implements Runnable {
    private static Semaphore availableRunways = null;
    private static Semaphore fuelStation = new Semaphore(1);

    public Airplane(Semaphore runs) {
        availableRunways = runs;
    }

    @Override
    public void run() {
        takeOff();
        randomFlightPractice();
        touchAndGo();
        randomFlightPractice();
        land();
        fuelUp();
    }

    public void takeOff() {
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] is requesting for a runway.");
        
        try {
            availableRunways.acquire();
            printTimestamp();
            System.out.println("Plane [" + Thread.currentThread().getName() + "] is taking off!");
            Thread.sleep(2600);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] has taken off!");
        availableRunways.release();
    }

    public void randomFlightPractice() {
        int random = (int)(Math.random() * 300 + 1); // random int from 0-300
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] is soaring through the sky.");
        try {
            Thread.sleep(100+random);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void touchAndGo() {
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] is looking to touch and go.");
        
        try {
            availableRunways.acquire();
            printTimestamp();
            System.out.println("Plane [" + Thread.currentThread().getName() + "] is touching!");
            Thread.sleep(300);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] is finished touching!");
        availableRunways.release();
    }

    public void land() {
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] is requesting to land.");
        
        try {
            availableRunways.acquire();
            printTimestamp();
            System.out.println("Plane [" + Thread.currentThread().getName() + "] is landing!");
            Thread.sleep(2600);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] has finished landing and gotten off the runway!");
        availableRunways.release();
    }

    public void fuelUp() {
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] is entering queue to refuel.");
        
        try {
            fuelStation.acquire();
            printTimestamp();
            System.out.println("Plane [" + Thread.currentThread().getName() + "] is refueling.");
            Thread.sleep(5000);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        printTimestamp();
        System.out.println("Plane [" + Thread.currentThread().getName() + "] has finished refueling and returned to the airplane hangar!");
        fuelStation.release();
    }

    public void printTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.print(dateFormat.format(date) + " ");
    }
}