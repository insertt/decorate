package github.cweijan.decorate.core;

import com.intellij.openapi.actionSystem.impl.ActionMenu;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.JBUI;
import github.cweijan.decorate.menu.ColourHoverListener;
import github.cweijan.decorate.util.WindowUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;

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

        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel.setLayout(flowLayout);

        JPanel minimizePanel = createMinimizePanel();
        JPanel decoratePanel = createDecoratePanel();
        JPanel exitPanel = createExitPanel();

        panel.add(minimizePanel);
        panel.add(decoratePanel);
        panel.add(exitPanel);

        JMenuBar menuBar = frame.getJMenuBar();
        menuBar.add(panel, menuBar.getMenuCount() + 1);
    }

    /**
     beauty intellij idea menubar
     */
    void beautyMenu(){

        JMenuBar menuBar = frame.getJMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        for(Component component : menuBar.getComponents()){
            if(component.getClass() != ActionMenu.class) continue;
            component.addMouseListener(new ColourHoverListener(component));
            ((ActionMenu) component).setMnemonicEnabled(false);
        }

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
        icon.setBorder(JBUI.Borders.empty(0, 8));
        if(showIcon){
            menuBar.add(icon, 0);
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

    @NotNull
    private JPanel createExitPanel(){

        JPanel exitPanel = new JPanel();
        JLabel exit = new JLabel(IconLoader.getIcon("/icon/ic_exit.png"));
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        exit.addMouseListener(new ColourHoverListener(exitPanel));
        exitPanel.addMouseListener(new ColourHoverListener(exitPanel));
        exitPanel.setBorder(JBUI.Borders.empty(2, 8));

        MouseAdapter decorateHandler = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        };
        exit.addMouseListener(decorateHandler);
        exitPanel.addMouseListener(decorateHandler);
        exitPanel.add(exit);
        return exitPanel;
    }

    @NotNull
    private JPanel createDecoratePanel(){

        JPanel decoratePanel = new JPanel();
        JLabel decorate = new JLabel(IconLoader.getIcon("/icon/ic_decorate_mode.png"));
        decorate.addMouseListener(new ColourHoverListener(decoratePanel));
        decorate.setHorizontalAlignment(SwingConstants.CENTER);
        decoratePanel.addMouseListener(new ColourHoverListener(decoratePanel));
        decoratePanel.setBorder(JBUI.Borders.empty(2, 8));
        MouseAdapter decorateHandler = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                WindowUtils.toggleWindow(frame);
            }
        };
        decorate.addMouseListener(decorateHandler);
        decoratePanel.addMouseListener(decorateHandler);
        decoratePanel.add(decorate);
        return decoratePanel;
    }

    @NotNull
    private JPanel createMinimizePanel(){

        JPanel minimizePanel = new JPanel();
        JLabel minimize = new JLabel(IconLoader.getIcon("/icon/minimize.png"));
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.addMouseListener(new ColourHoverListener(minimizePanel));
        minimizePanel.addMouseListener(new ColourHoverListener(minimizePanel));
        minimizePanel.setBorder(JBUI.Borders.empty(2, 8));
        MouseAdapter minimizeHandler = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                frame.setExtendedState(Frame.ICONIFIED);
            }
        };
        minimize.addMouseListener(minimizeHandler);
        minimizePanel.addMouseListener(minimizeHandler);
        minimizePanel.add(minimize);
        return minimizePanel;
    }

}
