package ukma.group.shop.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
	
	protected void add_entity_column(String name, int width)
	{
		TableColumn column = new TableColumn(table_columns.size());
		
		column.setPreferredWidth(width);
		column.setHeaderValue(name);
		
		table_column_model.addColumn(column);
		table_model.addColumn(name);
		table_columns.add(column);
	}

	protected void add_entity_column(String name)
	{
		add_entity_column(name, 16);
	}

	public EntityWindow(String title) 
	{
		super(title);

		table.setFillsViewportHeight(true);
		
		table_panel.setLayout(new BorderLayout());
		table_panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		table_panel.add(table, BorderLayout.CENTER);

		JScrollPane scroll_pane = new JScrollPane(table_panel);
		scroll_pane.setPreferredSize(new Dimension(140, 100));
		this.add(scroll_pane);
		
		table_panel.add(table);
		
		this.setVisible(false);
		this.setSize(500, 300);
		this.setLocation(500, 20);
		
		// make rows selectable other than cells
	    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	    table.setColumnSelectionAllowed(true);
	    table.setRowSelectionAllowed(false);

	    table.setColumnSelectionAllowed(false);
	    table.setRowSelectionAllowed(true);
		
	    // id is the default column since every entity has an id
		add_entity_column("id");
	}
	
	public void open()
	{
		update_table();
		this.setVisible(true);
	}

	
	protected void update_table()
	{
		
	}
	
	protected void add_row(Object[] data)
	{
		table_model.addRow(data);
	}
}
