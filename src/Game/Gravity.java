package Game;

public class Gravity extends Thread {
    public void run() {
        while (true)  {
            //jede sekunde wird versucht den stein mit der currentid nachunten zu bewegen
            Move.down();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
