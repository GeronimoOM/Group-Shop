package ukma.group.shop.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.sun.glass.events.WindowEvent;

import ukma.group.shop.dao.DaoManager;

public class MainWindow extends BasicWindow {
    private static MainWindow instance = new MainWindow();

    private JMenuBar menubar = new JMenuBar();

    private ItemWindow itemWindow = new ItemWindow();

    private SupplyWindow supplyWindow = new SupplyWindow();

    private OrderWindow orderWindow = new OrderWindow();

    public static MainWindow getInstance() {
        return instance;
    }

    private MainWindow() {
        super("MAIN");

        JMenu operate_menu = new JMenu("Operate");
        JMenuItem op_items = new JMenuItem("Items");
        op_items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemWindow.open();
            }
        });
        operate_menu.add(op_items);
        JMenuItem op_deps = new JMenuItem("Departments");
        operate_menu.add(op_deps);
        JMenuItem op_suppliers = new JMenuItem("Suppliers");
        operate_menu.add(op_suppliers);
        JMenuItem op_supplies = new JMenuItem("Supplies");
        op_supplies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supplyWindow.open();
            }
        });
        operate_menu.add(op_supplies);
	JMenuItem opOrder = new JMenuItem("Make Order");
	opOrder.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			orderWindow.open();
		}
	});
	operate_menu.add(opOrder);
        menubar.add(operate_menu);

        this.setJMenuBar(menubar);
        this.setLocation(20, 20);

        // close program on main window close
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("unused")
            public void windowClosing(WindowEvent windowEvent) {
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

    public ItemWindow getItemWindow() {
        return itemWindow;
    }

	public void setItemWindow(ItemWindow itemWindow) {
		this.itemWindow = itemWindow;
	}

	public OrderWindow getOrderWindow() {
		return orderWindow;
	}

	public void setOrderWindow(OrderWindow orderWindow) {
		this.orderWindow = orderWindow;
	}

    public SupplyWindow getSupplyWindow() {
        return supplyWindow;
    }

    public void setSupplyWindow(SupplyWindow supplyWindow) {
        this.supplyWindow = supplyWindow;
    }


}
