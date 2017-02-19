package ukma.group.shop.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * this is the template class you should extend from.
 * use ItemWindow as a reference on how to properly extend this
 */

public abstract class EntityWindow<T> extends BasicWindow 
{
	DefaultTableModel table_model = new DefaultTableModel();
	DefaultTableColumnModel table_column_model = new DefaultTableColumnModel();
	
	private JPanel table_panel = new JPanel();
	private JTable table = new JTable(table_model, table_column_model);
	private ArrayList<TableColumn> table_columns = new ArrayList<TableColumn>();

	private JPanel controls_panel = new JPanel();
	private JPanel entity_panel = new JPanel();
	private JPanel actions_panel = new JPanel();
	
	private Map<Integer, JTextField> entity_text_fields = new HashMap<>();
	private Map<Integer, JComboBox> entity_select_fields = new HashMap<>();
	private int entity_fields_amount = 0;

	private JButton clear_button = new JButton("Clear Selection");
	private JButton update_button = new JButton("Add/Update");
	private JButton remove_button = new JButton("Remove");
	
	private Long selectedId = null;
	
	protected EntityWindowAdapter<T> adapter;
	
	protected void add_entity_column(ColumnDefinition column_def)
	{
		TableColumn column = new TableColumn(table_columns.size());
		
		column.setPreferredWidth(column_def.getWidth());
		column.setHeaderValue(column_def.getName().toUpperCase());
		
		table_column_model.addColumn(column);
		table_model.addColumn(column_def.getName().toUpperCase());
		table_columns.add(column);

		
		if (column_def.isEditable())
		{
			JPanel entity_group = new JPanel(new GridLayout(2, 1));
	
			if (entity_fields_amount > 0)
			{
				JPanel filler = new JPanel();
				filler.setMaximumSize(new Dimension(10, 1));
				entity_panel.add(filler);
			}
			entity_panel.add(entity_group);
			
			JLabel text = new JLabel(column_def.getName().toUpperCase() + ":");
			text.setHorizontalAlignment(JLabel.CENTER);
			entity_group.add(text);
			
			if (column_def.getData() == null)
			{
				JTextField field = new JTextField(6);
				entity_text_fields.put(entity_fields_amount, field);
				entity_group.add(field);
			}
			else
			{
				JComboBox select = new JComboBox(column_def.getData());
				entity_select_fields.put(entity_fields_amount, select);
				entity_group.add(select);
			}
		}
		
		entity_fields_amount ++;
	}

	public EntityWindow(String title, List<ColumnDefinition> column_structure, final EntityWindowAdapter<T> adapter) 
	{
		super(title);
		
		this.adapter = adapter;
		
		controls_panel.add(entity_panel);
		controls_panel.add(actions_panel);
		controls_panel.setLayout(new GridLayout(2, 1));
		
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

		actions_panel.add(clear_button);
		clear_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				table.clearSelection();
				selectedId = null;
			}
		});
		
		actions_panel.add(update_button);
		update_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (selectedId != null)
				{
					// update the row in the table
					int row_to_update = -1;
					for (int row = 0; row < table.getModel().getRowCount(); row ++) 
					{
						Object val = table.getModel().getValueAt(row, 0);
						if (val != null && val.toString().equals(String.valueOf(selectedId)))
							row_to_update = row;
					}
					
					for (int i = 0; i < entity_fields_amount; i ++)
					{
						if (entity_text_fields.get(i) != null)
							table.getModel().setValueAt(entity_text_fields.get(i).getText(), row_to_update, i);
						if (entity_select_fields.get(i) != null)
							table.getModel().setValueAt(entity_select_fields.get(i).getSelectedItem(), row_to_update, i);
					}
				}
				else
				{
					// add to table
					ArrayList<Object> new_obj = new ArrayList<Object>();
					new_obj.add(selectedId);
					for (int i = 0; i < entity_fields_amount; i ++)
					{
						if (entity_text_fields.get(i) != null)
							new_obj.add(entity_text_fields.get(i).getText());
						if (entity_select_fields.get(i) != null)
							new_obj.add(entity_select_fields.get(i).getSelectedItem());
					}
					add_entity(adapter.rowToEntity(new_obj.toArray()));
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
		        	for (int i = 0; i < entity_fields_amount; i ++)
		        	{
		        		Object val = table.getValueAt(table.getSelectedRow(), i);
		        		if (entity_text_fields.get(i) != null)
		        			entity_text_fields.get(i).setText(val == null ? "" : val.toString());
		        		if (entity_select_fields.get(i) != null)
		        			entity_select_fields.get(i).setSelectedItem(val);
		        		
		        		selectedId = (Long)table.getValueAt(table.getSelectedRow(), 0);
		        	}
	        }
	    });
		
		for (ColumnDefinition column_def : column_structure)
			add_entity_column(column_def);
	}
	
	public void open()
	{
		add_entities(adapter.load());
		
		this.setVisible(true);
	}
	
	private void add_entity(T entity)
	{
		table_model.addRow(adapter.entityToRow(entity));
	}
	
	private void add_entities(List<T> entities)
	{
		for (T entity : entities)
			add_entity(entity);
	}
	
	public void add_button(JButton button)
	{
		actions_panel.add(button);
	}
}
