package ukma.group.shop.gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ukma.group.shop.dao.DaoManager;
import ukma.group.shop.dao.ItemDao;
import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Item;

public class ItemWindow extends EntityWindow<Item> {
	
	private static class ItemEntityWindowAdapter implements EntityWindowAdapter<Item> 
	{
		private ItemDao dao = DaoManager.getInstance().getItemDao();
		
		@Override
		public Item rowToEntity(Object[] row) {
			Long id = (Long)row[0];
			String name = (String)row[1];
			Long price = (Long)row[2];
			Long amount = (Long)row[3];
			Long min_amount = (Long)row[4];
			Department dep = (Department)row[5];
			return new Item(id, name, price, amount, min_amount, dep);
		}

		@Override
		public Object[] entityToRow(Item entity) {
			return new Object[]{ entity.getId(), entity.getName(), entity.getPrice(), entity.getAmount(), entity.getMinAmount(), entity.getDepartment() };
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
						new ColumnDefinition("id", false),
						new ColumnDefinition("name", 200),
						new ColumnDefinition("price"),
						new ColumnDefinition("amount"),
						new ColumnDefinition("min. amount"),
						new ColumnDefinition("department", 200, DaoManager.getInstance().getDepartmentDao().findAll().toArray())
						), 
			new ItemEntityWindowAdapter());
	}
}
