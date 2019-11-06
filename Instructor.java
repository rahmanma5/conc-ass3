import java.lang.Thread;
import java.util.concurrent.Semaphore;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Instructor implements Runnable {
    private static Semaphore instructors = null;

    public Instructor(Semaphore inst) {
        instructors = inst;
    }

    @Override
    public void run() {
        shouldIGoHome();
    }

    public void shouldIGoHome() {
        int goHome = (int)(Math.random() * 100 + 1); // random int from 0-100
        if (goHome > 50) {
            printTimestamp();
            System.out.println("Instructor [" + Thread.currentThread().getName() + "] is going home.");
            try {
                Thread.sleep(15000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        printTimestamp();
        System.out.println("Instructor [" + Thread.currentThread().getName() + "] is returning to the hangar.");
        instructors.release();

    }

    public void printTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.print(dateFormat.format(date) + " ");
    }

}