package misc.soundtrack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Soundtrack {

    private Clip soundtrack;
    public static Soundtrack imhotepTheme = new Soundtrack("/Imhotep_Soundtrack.wav");


    public Soundtrack(String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Soundtrack.class.getResource(fileName));
            soundtrack = AudioSystem.getClip();
            soundtrack.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            if (soundtrack != null) {
                new Thread() {
                    public void run() {
                        synchronized (soundtrack) {
                            soundtrack.stop();
                            soundtrack.setFramePosition(0);
                            soundtrack.start();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (soundtrack == null) return;
        soundtrack.stop();
    }

    public void loop() {
        try {
            if (soundtrack != null) {
                new Thread() {
                    public void run() {
                        synchronized (soundtrack) {
                            soundtrack.stop();
                            soundtrack.setFramePosition(0);
                            soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return soundtrack.isActive();
    }

}
