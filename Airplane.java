import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class Airplane implements Runnable {
    @Override
    public void run() {
        System.out.println("Airplane in use by " + Thread.currentThread().getName());
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