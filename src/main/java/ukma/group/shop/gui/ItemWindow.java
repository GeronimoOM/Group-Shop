package ukma.group.shop.gui;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ukma.group.shop.entity.Item;

public class ItemWindow extends EntityWindow {

	public ItemWindow() {
		super("Items");

		// add columns for the main table and specify some of the widths
		add_entity_column("name", 200);
		add_entity_column("price");
		add_entity_column("amount");
		add_entity_column("department", 200);
	}
}
