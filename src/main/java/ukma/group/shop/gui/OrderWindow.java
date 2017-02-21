package ukma.group.shop.gui;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ukma.group.shop.dao.DaoManager;
import ukma.group.shop.dao.OrderDao;
import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Employee;
import ukma.group.shop.entity.Order;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.entity.Supply;

public class OrderWindow extends EntityWindow<Order> {
	
	private static class OrderEntityWindowAdapter implements EntityWindowAdapter<Order> 
	{
		private OrderDao dao = DaoManager.getInstance().getOrderDao();
		
		@Override
		public Order rowToEntity(Object[] row) {
			Long id = (Long)row[0];
			Date date = (Date)row[1];
			Supplier supplier = (Supplier)row[2];
			Supply supply = (Supply)row[3];
			Employee employee = (Employee)row[4];
//			Department dep = (Department)row[5];
			return new Order(id, date, supplier, supply, employee);
		}

		@Override
		public Object[] entityToRow(Order entity) {
			return new Object[]{ entity.getId(), entity.getDate(), entity.getSupplier(), entity.getSupply(), entity.getEmployee()};
		}
		
		@Override
		public void onUpdate(Order entity) {
			dao.update(entity);
		}
		
		@Override
		public void onRemove(Order entity) {
			dao.delete(entity.getId());
		}
		
		@Override
		public void onAdd(Order entity) {
			dao.persist(entity);
		}

		@Override
		public List<Order> load() {
			return dao.findAll();
		}
	};

	public OrderWindow() 
	{
		super(
			"Make Order", 
			Arrays.asList(
						new ColumnDefinition("id123", false),
						new ColumnDefinition("name", 200),
						new ColumnDefinition("price"),
						new ColumnDefinition("amount"),
						new ColumnDefinition("min. amount"),
						new ColumnDefinition("department", 200, DaoManager.getInstance().getDepartmentDao().findAll().toArray())
						), 
			new OrderEntityWindowAdapter());
	}
}
