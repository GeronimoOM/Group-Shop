package ukma.group.shop.gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BasicWindow extends JFrame 
{
	
	final Color c_back = new Color(30, 30, 40);
	final Color c_fore = new Color(255, 255, 255);
	final Color c_sel = new Color(30, 160, 120);
	final Color c_bor = new Color(30, 160, 120);
	
	public BasicWindow(String title)
	{
		this.setTitle("SHOP :: " + title);

		UIManager.put("Panel.background", c_back);
		
		UIManager.put("MenuItem.background", c_back);
		UIManager.put("MenuItem.foreground", c_fore);
		UIManager.put("MenuItem.selectionBackground", c_sel);
		UIManager.put("MenuBar.background", c_back);
		UIManager.put("Menu.foreground", c_fore);
		UIManager.put("Menu.selectionBackground", c_sel);
		
		
		UIManager.put("Button.background", c_back);
		UIManager.put("Button.foreground", c_fore);
		
		UIManager.put("Label.background", c_back);
		UIManager.put("Label.foreground", c_fore);
		
		UIManager.put("TextField.background", c_back);
		UIManager.put("TextField.foreground", c_fore);
		UIManager.put("TextField.caretForeground", c_fore);
		UIManager.put("TextField.caretBlinkRate", 100);
		
		UIManager.put("Table.background", c_back);
		UIManager.put("Table.foreground", c_fore);
		UIManager.put("Table.selectionBackground", c_sel);
		UIManager.put("Table.selectionForeground", c_fore);
		
		this.setVisible(true);
		this.getContentPane().setLayout(new BorderLayout());
	}

}
