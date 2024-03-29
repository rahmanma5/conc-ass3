import java.lang.Thread;
import java.util.concurrent.Semaphore;



public class Airport {
    private static int numOfRunways = 1;
    private static int numOfStudents = 2;
    private static int numOfPlanes = 1;
    private static int numOfInstructors = 1;
    public static void main(String[] args) {
        Student.setValues(numOfPlanes,numOfRunways,numOfInstructors);
        Thread studentList[] = new Thread[numOfStudents];
        for (int i=0;i<numOfStudents;i++) {
            Runnable runner = new Student();
            studentList[i] = new Thread(runner);
        }

        for (int i=0;i<numOfStudents;i++) {
            studentList[i].start();
        }

        for (int i=0;i<numOfStudents;i++) {
            try {
                studentList[i].join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    
}


