package Music;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class Data extends JFrame {
    public static void playMusic(File file, double volume) {// 背景音乐播放
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);    //绝对路径
            AudioFormat aif = ais.getFormat();
            final SourceDataLine sdl;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
            sdl = (SourceDataLine) AudioSystem.getLine(info);
            sdl.open(aif);
            sdl.start();
            FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
            // value可以用来设置音量，从0-2.0
            //double volume = 0.5;
            float dB = (float) (Math.log(volume == 0.0 ? 0.0001 : volume) / Math.log(10.0) * 20.0);
            fc.setValue(dB);
            int nByte = 0;
            final int SIZE = 1024 * 64;
            byte[] buffer = new byte[SIZE];
            while (nByte != -1) {
                nByte = ais.read(buffer, 0, SIZE);
                sdl.write(buffer, 0, nByte);
            }
            sdl.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        new Thread(()->{while(true) {
            Data.playMusic(new File("D:\\IdeaProjects\\pro_try\\src\\群青.wav"));} //while中的true可换成参数来控制音乐的停止播放
        }).start();// Lambda表达式
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (true) {
//					playMusic(filename);
//				}
//			}
//		}).start();// 开启一个线程用来播放音乐
    }*/

}
