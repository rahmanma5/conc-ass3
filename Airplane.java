import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class Airplane implements Runnable {
    private static Semaphore availableRunways = null;

    @Override
    public void run() {
        minimumFlightPractice();
    }

    public void minimumFlightPractice() {
        System.out.println("Plane [" + Thread.currentThread().getName() + "] is soaring through the sky");
        for (int i=0;i<5;i++) {
            //System.out.println("WEE!");
            try {
                Thread.sleep(200);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done flying!: " + Thread.currentThread().getName());
    }
}