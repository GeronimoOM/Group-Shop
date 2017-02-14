package ukma.group.shop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import com.sun.glass.events.WindowEvent;

public class MainWindow extends BasicWindow 
{
	private JMenuBar menubar = new JMenuBar();
	
	public ItemWindow item_window;
	
	public MainWindow()
	{
		super("MAIN");
		
		JMenu operate_menu = new JMenu("Operate");
		JMenuItem op_items = new JMenuItem("Items");
		op_items.addActionListener((ActionEvent event) -> { item_window.open(); });
		operate_menu.add(op_items);
		menubar.add(operate_menu);
		
        this.setJMenuBar(menubar);
		this.setSize(300, 300);
		this.setLocation(20, 20);

        // close program on main window close
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() 
        {
        	@SuppressWarnings("unused")
        	public void windowClosing(WindowEvent windowEvent) 
        	{ 
        		System.exit(0); 
        	}        
        });
	}

}
