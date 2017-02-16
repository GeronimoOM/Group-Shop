package ukma.group.shop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * this is the template class you should extend from.
 * use ItemWindow as a reference on how to properly extend this
 */

public class EntityWindow extends BasicWindow 
{
	DefaultTableModel table_model = new DefaultTableModel();
	DefaultTableColumnModel table_column_model = new DefaultTableColumnModel();
	
	private JPanel table_panel = new JPanel();
	private JTable table = new JTable(table_model, table_column_model);
	private ArrayList<TableColumn> table_columns = new ArrayList<TableColumn>();

	private JPanel controls_panel = new JPanel();
	private JPanel entity_panel = new JPanel();
	private JPanel actions_panel = new JPanel();
	
	private ArrayList<JTextField> entity_fields = new ArrayList<JTextField>();

	public JButton save_button = new JButton("SAVE");
	private JButton update_button = new JButton("Add/Update");
	private JButton remove_button = new JButton("Remove");
	
	protected void add_entity_column(String name, int width)
	{
		TableColumn column = new TableColumn(table_columns.size());
		
		column.setPreferredWidth(width);
		column.setHeaderValue(name.toUpperCase());
		
		table_column_model.addColumn(column);
		table_model.addColumn(name.toUpperCase());
		table_columns.add(column);

		
		JPanel entity_group = new JPanel(new GridLayout(2, 1));

		if (entity_fields.size() > 0)
		{
			JPanel filler = new JPanel();
			filler.setMaximumSize(new Dimension(10, 1));
			entity_panel.add(filler);
		}
		entity_panel.add(entity_group);
		
		JLabel text = new JLabel(name.toUpperCase() + ":");
		text.setHorizontalAlignment(JLabel.CENTER);
		entity_group.add(text);
		
		JTextField field = new JTextField(6);
		entity_fields.add(field);
		entity_group.add(field);
	}

	protected void add_entity_column(String name)
	{
		add_entity_column(name, 16);
	}

	public EntityWindow(String title) 
	{
		super(title);
		
		controls_panel.add(entity_panel);
		controls_panel.add(actions_panel);
		controls_panel.setLayout(new GridLayout(2, 1));
		
		actions_panel.add(save_button);
		
		actions_panel.add(remove_button);
		remove_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int index = table.getSelectedRow();
			    if (index != -1) 
			        ((DefaultTableModel)table.getModel()).removeRow(table.convertRowIndexToModel(index));
			}
		});
		
		actions_panel.add(update_button);
		update_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// look for needed id
				int row_to_update = -1;
				for (int row = 0; row < table.getModel().getRowCount(); row ++) 
				{
					Object val = table.getModel().getValueAt(row, 0);
					if (val != null && val.toString().equals(entity_fields.get(0).getText()))
						row_to_update = row;
				}
				
				// update the row in the table
				if (row_to_update >= 0)
					for (int i = 0; i < entity_fields.size(); i ++)
						table.getModel().setValueAt(entity_fields.get(i).getText(), row_to_update, i);
				// add to table
				else
				{
					ArrayList<Object> new_obj = new ArrayList<Object>();
					for (int i = 0; i < entity_fields.size(); i ++)
						new_obj.add(entity_fields.get(i).getText());
					add_entity(new_obj.toArray());
				}
			}
		});
		
		table_panel.setLayout(new BorderLayout());
		table_panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		table_panel.add(table, BorderLayout.CENTER);

		JScrollPane scroll_pane = new JScrollPane(table);
		scroll_pane.setPreferredSize(new Dimension(140, 100));
		this.add(scroll_pane, BorderLayout.CENTER);

		this.add(controls_panel, BorderLayout.SOUTH);
		entity_panel.setLayout(new BoxLayout(entity_panel, BoxLayout.X_AXIS));
		
		this.setVisible(false);
		this.setSize(800, 500);
		this.setLocation(500, 20);
		
		// make rows selectable other than cells
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    table.setColumnSelectionAllowed(true);
	    table.setRowSelectionAllowed(false);

	    table.setColumnSelectionAllowed(false);
	    table.setRowSelectionAllowed(true);
	    
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if (table.getSelectedRow() != -1)
		        	for (int i = 0; i < entity_fields.size(); i ++)
		        	{
		        		Object val = table.getValueAt(table.getSelectedRow(), i);
		        		entity_fields.get(i).setText(val == null ? "" : val.toString());
		        	}
	        }
	    });
		
	    // id is the default column since every entity has an id
		add_entity_column("id");
	}
	
	public void open()
	{
		update_table();
		this.setVisible(true);
	}
	
	public void add_entity(Object[] data)
	{
		table_model.addRow(data);
	}
	
	public void add_button(JButton button)
	{
		actions_panel.add(button);
	}

	private void update_table()
	{
		
	}	
}
