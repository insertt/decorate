package github.cweijan.decorate.util;

import github.cweijan.decorate.state.WindowState;

import javax.swing.*;
import java.awt.*;

public class WindowUtils{

    private static GraphicsEnvironment environment;
    private static Rectangle rectangle;

    static{
        environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        rectangle = environment.getMaximumWindowBounds();
    }

    public static int getWidth(){

        return rectangle.width;
    }

    public static int getHeight(){

        return rectangle.height;
    }

    public static boolean isMaximize(JFrame frame){

        return (frame.getWidth() == getWidth() && frame.getHeight() == getHeight()) && frame.getX() == 0 && frame.getY() == 0;
    }

    public static void toggleWindow(JFrame frame){

        if(isMaximize(frame)){
            WindowState.recoveryWindow(frame, false);
        } else{
            WindowState.saveWindowState(frame);
            frame.setBounds(0, 0, WindowUtils.getWidth(), WindowUtils.getHeight());
        }
    }
}
