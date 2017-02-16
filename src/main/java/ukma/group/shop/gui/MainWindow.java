package ukma.group.shop.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
		JMenuItem op_deps = new JMenuItem("Departments");
		operate_menu.add(op_deps);
		JMenuItem op_suppliers = new JMenuItem("Suppliers");
		operate_menu.add(op_suppliers);
		menubar.add(operate_menu);
		
        this.setJMenuBar(menubar);
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

        JPanel panel = new JPanel(new BorderLayout());
        JLabel main_text = new JLabel("TOY SHOP");
        panel.add(main_text, BorderLayout.CENTER);
        main_text.setHorizontalAlignment(JLabel.CENTER);
        this.add(panel, BorderLayout.CENTER);

		this.setSize(300, 200);
	}

}
