package github.cweijan.decorate.window;

import github.cweijan.decorate.core.WindowEnhance;
import github.cweijan.decorate.util.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.atomic.AtomicReference;

public class WindowSizer{

    private JFrame frame;
    private int x;
    private int y;

    public WindowSizer(JFrame frame){

        this.frame = frame;
    }

    public void init(){

        AtomicReference<CursorState> cursorState = new AtomicReference<>();

        MouseMotionAdapter cursorChanger = new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){

                int width = frame.getWidth();
                int height = frame.getHeight();
                boolean isNormal = true;
                // TODO 获取最下面的组件
                WindowEnhance.disableDragable();
                if(e.getX() <= 3){
                    //西
                    frame.getJMenuBar().setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                    frame.getContentPane().setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                    cursorState.set(CursorState.west);
                    isNormal = false;
                }

                if(e.getX() >= width - 3){
                    //东
                    frame.getJMenuBar().setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                    frame.getContentPane().setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                    cursorState.set(CursorState.east);
                    isNormal = false;
                }

                if(e.getY() <= 3){
                    // 北
                    frame.getJMenuBar().setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                    cursorState.set(CursorState.north);
                    isNormal = false;
                }
                if(e.getX() <= 3 && e.getY() <= 3){
                    //西北
                    frame.getJMenuBar().setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                    cursorState.set(CursorState.nw);
                    isNormal = false;
                }
                if(e.getX() >= width - 3 && e.getY() <= 3){
                    //东北
                    frame.getJMenuBar().setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                    cursorState.set(CursorState.ne);
                    isNormal = false;
                }
                if(e.getY() >= height - 3){
                    // 南
                    frame.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                    cursorState.set(CursorState.south);
                    isNormal = false;
                }

                if(e.getX() <= 3 && e.getY() >= height - 3){
                    // 西南
                    frame.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                    cursorState.set(CursorState.sw);
                    isNormal = false;
                }
                if(e.getX() >= width - 3 && e.getY() >= height - 3){
                    // 东南
                    frame.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                    cursorState.set(CursorState.se);
                    isNormal = false;
                }

                if(isNormal){
                    frame.getJMenuBar().setCursor(Cursor.getDefaultCursor());
                    frame.getContentPane().setCursor(Cursor.getDefaultCursor());
                    WindowEnhance.enableDragable();
                }

            }

            @Override
            public void mouseDragged(MouseEvent e){
                if(WindowEnhance.draging || null==cursorState.get()){
                    return;
                }
                switch(cursorState.get()){
                    case west:
                        frame.setBounds(frame.getX() + (e.getX() - x), frame.getY(), frame.getWidth() - (e.getX() - x), frame.getHeight());
                        break;
                    case east:
                        frame.setBounds(frame.getX(), frame.getY(), frame.getWidth() - (x - e.getX()), frame.getHeight());
                        x=e.getX();
                        break;
                    case north:
                        frame.setBounds(frame.getX(), frame.getY() + (e.getY() - y), frame.getWidth(), frame.getHeight() - (e.getY() - y));
                        break;
                    case nw:
                        frame.setBounds(frame.getX() + (e.getX() - x), frame.getY() + (e.getY() - y), frame.getWidth() - (e.getX() - x), frame.getHeight() - (e.getY() - y));
                        break;
                    case ne:
                        frame.setBounds(frame.getX(), frame.getY() + (e.getY() - y), frame.getWidth() - (x - e.getX()), frame.getHeight() - (e.getY() - y));
                        x=e.getX();
                        break;
                    case south:
                        frame.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight() - (y - e.getY()));
                        break;
                    case sw:
                        frame.setBounds(frame.getX() + (e.getX() - x), frame.getY(), frame.getWidth() - (e.getX() - x), frame.getHeight() - (y - e.getY()));
                        break;
                    case se:
                        frame.setBounds(frame.getX(), frame.getY(), frame.getWidth() - (x - e.getX()), frame.getHeight() - (y - e.getY()));
                        x=e.getX();
                        break;
                }

            }
        };

        MouseAdapter windowChanger = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){

                x = e.getX();
                y = e.getY();
            }

        };
        frame.getContentPane().addMouseListener(windowChanger);
        frame.getContentPane().addMouseMotionListener(cursorChanger);
        frame.getJMenuBar().addMouseListener(windowChanger);
        frame.getJMenuBar().addMouseMotionListener(cursorChanger);

    }

}
