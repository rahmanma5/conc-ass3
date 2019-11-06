import java.lang.Thread;
import java.util.concurrent.Semaphore;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Student implements Runnable {
    
    private static Semaphore availablePlanes = null;
    private static Semaphore availableRunways = null;
    private static Semaphore availableInstructors = null;
    private Thread plane = null;
    private Thread instructor = null;

    public static void setValues(int numOfPlanes, int numOfRunways, int numOfInstructors) {
        availablePlanes = new Semaphore(numOfPlanes);
        availableInstructors = new Semaphore(numOfInstructors);
        availableRunways = new Semaphore(numOfRunways);
    }

    @Override
    public void run() {
        while(true) {
            getAirplane();
            getInstructor();
            flyAround();
            returnPlane();
            freeInstructor();
            takeABreak();
        }
    }

    public void getAirplane() {
        try {
            availablePlanes.acquire();
            Runnable runner = new Airplane(availableRunways);
            plane = new Thread(runner);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getInstructor() {
        try {
            printTimestamp();
            System.out.println("Student [" + Thread.currentThread().getName() + "] is looking for an instructor.");
            availableInstructors.acquire();
            
            Runnable runner = new Instructor(availableInstructors);
            instructor = new Thread(runner);
            printTimestamp();
            System.out.println("Student [" + Thread.currentThread().getName() + "] found Instructor [" + instructor.getName() + "].");
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void flyAround() {
        printTimestamp();
        System.out.println("Plane [" + plane.getName() + "] being boarded by: " + Thread.currentThread().getName());
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

    public void freeInstructor() {
        instructor.start();
    }

    public void takeABreak() {
        printTimestamp();
        System.out.println("Student [" + Thread.currentThread().getName() + "] is taking a break.");
        try {
            Thread.sleep(10000);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.print(dateFormat.format(date) + " ");
    }
}