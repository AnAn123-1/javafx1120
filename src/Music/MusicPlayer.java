package Music;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class MusicPlayer {
    private AudioInputStream audioIn;
    private SourceDataLine sourceDataLine;
    private File file;
    private volatile boolean isLoop = false;
    private volatile boolean isPlaying;
    private float newVolumn = 7;

    private PlayThread playThread;

    /*public static void main(String[] args) {
        try {
            MusicPlayer player = new MusicPlayer("D:\\IdeaProjects\\pro_try\\src\\群青.wav");

            player.setVolumn(6f).play();
            System.out.println("开始播放");

            System.out.println("是否暂停？");
            Scanner sc = new Scanner(System.in);
            int isOver = sc.nextInt();
            if (isOver == 1) {
                player.over();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public MusicPlayer(String srcPath) {
        file = new File(srcPath);
    }

    /**
     * 播放音乐
     */
    public void play() {
        if (playThread == null) {
            playThread = new PlayThread();
            playThread.start();
        }
    }

    /**
     * 结束音乐
     */
    public void over() {
        isPlaying = false;
        if (playThread != null) {
            playThread = null;
        }
    }

    /**
     * 设置循环播放
     *
     * @param isLoop
     * @return 返回当前对象
     */
    public MusicPlayer setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        return this;
    }

    /**
     * -80.0~6.0206测试,越小音量越小
     *
     * @param newVolumn
     * @return 返回当前对象
     */
    public MusicPlayer setVolumn(float newVolumn) {
        this.newVolumn = newVolumn;
        return this;
    }

    /**
     * 异步播放线程
     */

    public class PlayThread extends Thread {
        @Override
        public void run() {
            isPlaying = true;
            setLoop(true);
            do {
//				isPlaying = true;

                SourceDataLine sourceDataLine = null;
                BufferedInputStream bufIn = null;
                AudioInputStream audioIn = null;
                try {
                    bufIn = new BufferedInputStream(new FileInputStream(file));
                    audioIn = AudioSystem.getAudioInputStream(bufIn); // 可直接传入file

                    AudioFormat format = audioIn.getFormat();
                    sourceDataLine = AudioSystem.getSourceDataLine(format);
                    sourceDataLine.open();
                    // 必须open之后
                    if (newVolumn != 7) {
                        FloatControl control = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
//						System.out.println(control.getMaximum());
//						System.out.println(control.getMinimum());
                        control.setValue(newVolumn);
                    }

                    sourceDataLine.start();
                    byte[] buf = new byte[512];
//					System.out.println(audioIn.available());
                    int len = -1;
                    while (isPlaying && (len = audioIn.read(buf)) != -1) {
                        sourceDataLine.write(buf, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    if (sourceDataLine != null) {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                    try {
                        if (bufIn != null) {
                            bufIn.close();
                        }
                        if (audioIn != null) {
                            audioIn.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } while (isPlaying && isLoop);
        }
    }


}