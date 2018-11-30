package github.cweijan.decorate.state;

import github.cweijan.decorate.util.WindowUtils;

import javax.swing.*;

public class WindowState{

    private static int y;
    private static int x;
    private static int width;
    private static int height;
    private static boolean isSave = false;

    public static void saveWindowState(JFrame frame){

        x = frame.getX();
        y = frame.getY();
        width = frame.getWidth();
        height = frame.getHeight();
        isSave = true;
    }

    public static void recoveryWindow(JFrame frame, boolean init){

        if(isSave){
            frame.setBounds(x, y, width, height);
        } else if(init){
            frame.setBounds(100, 100,frame.getWidth(), frame.getHeight());
        } else{
            frame.setBounds(100, 100, WindowUtils.getWidth() / 3 * 2, WindowUtils.getHeight() / 3 * 2);
        }

    }

    public static void maximizeWindow(JFrame frame){

        frame.setBounds(0, 0, WindowUtils.getWidth(), WindowUtils.getHeight());
    }
}
