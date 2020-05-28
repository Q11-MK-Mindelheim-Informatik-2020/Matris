package Effects;

import Var.Var;

import javax.sound.sampled.Clip;

public class SoundHandler implements Runnable{
    private Clip clip;
    public SoundHandler(Clip clip) {
        this.clip = clip;
    }
    @Override
    public void run() {
        clip.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Var.stopSignal = false;
        while(true) {
                    if(Var.stopSignal){
                        clip.stop();
                        clip.flush();
                        clip.close();
                        return;
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
            }
        }
    }

}
