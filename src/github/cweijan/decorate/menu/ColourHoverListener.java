package github.cweijan.decorate.menu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColourHoverListener extends MouseAdapter{

    private Component component;
    private Color defaultColor;

    public ColourHoverListener(Component component){
        this.component = component;
        this.defaultColor = component.getBackground();
    }

    @Override
    public void mouseExited(MouseEvent e){
        component.setBackground(defaultColor);
    }

}
