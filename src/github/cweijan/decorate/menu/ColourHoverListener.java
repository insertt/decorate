package github.cweijan.decorate.menu;

import com.intellij.openapi.actionSystem.impl.ActionMenu;

import javax.swing.*;
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
    public void mouseEntered(MouseEvent e){

        component.setBackground(Color.decode("#434C59"));
        updateSubMenuUi(component);

    }

    @Override
    public void mouseExited(MouseEvent e){
        component.setBackground(defaultColor);
    }

    @Override
    public void mousePressed(MouseEvent e){
        updateSubMenuUi(component);
    }

    private void updateSubMenuUi(Component component){

        if(!(component instanceof ActionMenu)) return;
        ActionMenu actionMenu = (ActionMenu) component;

        MenuElement[] subElements = actionMenu.getPopupMenu().getSubElements();
        for(MenuElement subElement : subElements){
            if(subElement instanceof JMenuItem){
                JMenuItem actionMenuItem = (JMenuItem) subElement;
                actionMenuItem.setUI(new ColourMenuItemUi());
            }
        }
    }

}
