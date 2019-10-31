import java.lang.Thread;
import java.util.concurrent.Semaphore;


public class Airport {
    public static void main(String[] args) {
        Runnable runner = new Airplane();
        Thread t1 = new Thread(runner);
        Thread t2 = new Thread(runner);

        t1.start();
        t2.start();
        try {
            t1.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }



    }

    
}


