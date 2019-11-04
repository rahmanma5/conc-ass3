import java.lang.Thread;
import java.util.concurrent.Semaphore;

class Student implements Runnable {
    
    private static Semaphore availablePlanes = null;
    private Thread plane = null;

    public static void setNumberOfPlanes(int num) {
        availablePlanes = new Semaphore(num);
    }

    @Override
    public void run() {
        getAirplane();
        flyAround();
        returnPlane();
    }

    public void getAirplane() {
        try {
            availablePlanes.acquire();
            Runnable runner = new Airplane();
            plane = new Thread(runner);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public void flyAround() {
        System.out.println("Plane being boarded by: " + Thread.currentThread().getName());
        plane.start();
        try {
            plane.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void returnPlane() {
        availablePlanes.release();
    }
}