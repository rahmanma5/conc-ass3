import java.lang.Thread;
import java.util.concurrent.Semaphore;

class Student implements Runnable {
    
    private static Semaphore availablePlanes = null;
    private static Semaphore availableRunways = null;
    private static Semaphore availableInstructors = null;
    private static Semaphore fuelPump = new Semaphore(1);
    private Thread plane = null;
    private Thread instructor = null;

    public static void setValues(int numOfPlanes, int numOfRunways, int numOfInstructors) {
        availablePlanes = new Semaphore(numOfPlanes);
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

    public void getInstructor() {
        try {
            availableInstructors.acquire();
            Runnable runner = new Instructor();
            instructor = new Thread(runner);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void flyAround() {
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
}