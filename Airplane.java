import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class Airplane implements Runnable {
    static Semaphore sem = new Semaphore(1);
    @Override
    public void run() {
        try {
            sem.acquire();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.print("Airplane in use by " + Thread.currentThread().getId());
        for (int i=0;i<5;i++) {
            System.out.println("WEE!");
            
            try {
                Thread.sleep(200);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("Done flying!");
        sem.release();
    }
}