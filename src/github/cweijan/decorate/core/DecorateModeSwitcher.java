package github.cweijan.decorate.core;

import com.intellij.openapi.wm.IdeFrame;
import com.intellij.ui.ScreenUtil;
import github.cweijan.decorate.state.WindowState;
import github.cweijan.decorate.window.WindowSizer;

import javax.swing.*;
import java.awt.*;

public class DecorateModeSwitcher{

    public void decorate(final IdeFrame ideFrame){

        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ideFrame.getComponent());

        new WindowSizer(frame).init();
        WindowEnhance windowEnhance = new WindowEnhance(frame);
        windowEnhance.showIconInMenu();
        windowEnhance.createMenuTool();
        windowEnhance.beautyMenu();
        windowEnhance.dragAble(frame.getJMenuBar());
        setDecorateMode(frame);
        WindowState.maximizeWindow(frame);
    }

    private static void setDecorateMode(JFrame frame){

        if(frame == null) return;
        GraphicsDevice device = ScreenUtil.getScreenDevice(frame.getBounds());
        if(device == null) return;
        try{
            frame.getRootPane().putClientProperty(ScreenUtil.DISPOSE_TEMPORARY, Boolean.TRUE);
            frame.getRootPane().putClientProperty("oldBounds", frame.getBounds());
            frame.dispose();
            frame.setUndecorated(true);
        } finally{
            frame.setBounds(device.getDefaultConfiguration().getBounds());
            frame.setVisible(true);
            frame.getRootPane().putClientProperty(ScreenUtil.DISPOSE_TEMPORARY, null);
        }
    }
}
