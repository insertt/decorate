package github.cweijan.decorate.core;

import com.intellij.openapi.util.IconLoader;
import github.cweijan.decorate.util.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WindowEnhance{

    private int xOld = 0;
    private int yOld = 0;
    private JFrame frame;
    private static boolean disableDragAble = false;
    public static boolean draging = false;

    WindowEnhance(JFrame frame){

        this.frame = frame;
    }

    /**
     create minimiz、maximize、close button for JMenuBar
     */
    void createMenuTool(){

        JMenuBar menuBar = frame.getJMenuBar();
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel.setLayout(flowLayout);
        panel.setBackground(null);
        JLabel minimize = new JLabel(IconLoader.getIcon("/icon/minimize.png"));
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                frame.setExtendedState(Frame.ICONIFIED);
            }
        });
        JLabel decorate = new JLabel(IconLoader.getIcon("/icon/ic_decorate_mode.png"));
        decorate.setHorizontalAlignment(SwingConstants.CENTER);
        decorate.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                WindowUtils.toggleWindow(frame);
            }
        });
        JLabel exit = new JLabel(IconLoader.getIcon("/icon/ic_exit.png"));
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        exit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        panel.add(minimize);
        panel.add(decorate);
        panel.add(exit);
        menuBar.add(panel, menuBar.getMenuCount() + 1);
    }

    /**
     beauty intellij idea menubar
     */
    void beautyMenu(){

        JMenuBar menuBar = frame.getJMenuBar();
        Font font = new Font("微软雅黑", Font.PLAIN, 25);
        menuBar.setBackground(Color.getColor("303845"));
        menuBar.setBorder(BorderFactory.createCompoundBorder(menuBar.getBorder(), BorderFactory.createEmptyBorder(5, 6, 4, 6)));
        menuBar.setFont(font);

        menuBar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                if(e.getClickCount() == 2){
                    WindowUtils.toggleWindow(frame);
                }
            }
        });

    }

    /**
     show application icon in menu left side
     */
    void showIconInMenu(){

        JMenuBar menuBar = frame.getJMenuBar();
        boolean showIcon = true;
        Icon appIcon = null;
        try{
            appIcon = new ImageIcon(frame.getIconImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } catch(RuntimeException e){
            showIcon = false;
        }

        JLabel icon = new JLabel(appIcon);
        JLabel iconOffset1 = new JLabel(" ");
        JLabel iconOffset2 = new JLabel(" ");
        if(showIcon){
            menuBar.add(iconOffset1, 0);
            menuBar.add(icon, 1);
            menuBar.add(iconOffset2, 2);
        }

    }

    public static void disableDragable(){

        disableDragAble = true;
    }

    public static void enableDragable(){

        disableDragAble = false;
    }

    /**
     let component can dragable whole JFrame
     */
    public void dragAble(Component component){

        component.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent e){

                xOld = e.getX();
                yOld = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e){

                draging = false;
            }
        });

        component.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){

                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                if(!disableDragAble){
                    frame.setLocation(xx, yy);
                    draging = true;
                }
            }
        });

    }

}
