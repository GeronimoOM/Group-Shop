package ukma.group.shop.gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ukma.group.shop.dao.DaoManager;
import ukma.group.shop.dao.ItemDao;
import ukma.group.shop.entity.Item;

public class ItemWindow extends EntityWindow<Item> {
	
	private static class ItemEntityWindowAdapter implements EntityWindowAdapter<Item> 
	{
		private ItemDao dao = DaoManager.getInstance().getItemDao();
		
		@Override
		public Item rowToEntity(Object[] row) {
			return new Item((Long)row[0], (String)row[1], (Long)row[2], (Long)row[3], (Long)row[4], (Long)row[5]);
		}

		@Override
		public Object[] entityToRow(Item entity) {
			return new Object[]{ entity.getId(), entity.getName(), entity.getPrice(), entity.getAmount(), entity.getMinAmount(), entity.getDepartmentId() };
		}
		
		@Override
		public void onUpdate(Item entity) {
			dao.update(entity);
		}
		
		@Override
		public void onRemove(Item entity) {
			dao.delete(entity.getId());
		}
		
		@Override
		public void onAdd(Item entity) {
			dao.persist(entity);
		}

		@Override
		public List<Item> load() {
			return dao.findAll();
		}
	};

	public ItemWindow() 
	{
		super(
			"Items", 
			Arrays.asList(
						new ColumnDefinition("id"),
						new ColumnDefinition("name", 200),
						new ColumnDefinition("price"),
						new ColumnDefinition("amount"),
						new ColumnDefinition("min. amount"),
						new ColumnDefinition("department", 200)
						), 
			new ItemEntityWindowAdapter());
	}
}
