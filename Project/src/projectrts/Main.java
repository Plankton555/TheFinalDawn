package projectrts;



import projectrts.controller.*;

/**
 * The entry point of the application.
 * @author Heqir
 *
 */
public class Main {
    public static void main(String[] args) {
        AppController app = new AppController();
        app.start();
    }
}
