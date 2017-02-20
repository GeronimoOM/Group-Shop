package ukma.group.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.ItemDao;
import ukma.group.shop.dao.LongHandler;
import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.entity.pojo.ItemPrice;

public class ItemDaoImpl implements ItemDao {
	
	private static final String SQL_SELECT_ITEMS =
			"SELECT i.id AS i_id, i.name AS i_name, price, amount, min_amount, department_id, d.id AS d_id, d.name AS d_name  "
			+ "FROM sh_items i INNER JOIN sh_departments d ON i.department_id = d.id";
	private static final String SQL_INSERT_ITEM = "INSERT INTO sh_items (name, price, amount, min_amount, department_id) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_ITEM = "UPDATE sh_items SET name=?, price=?, amount=?, min_amount=? WHERE id=?";
	private static final String SQL_DELETE_ITEM = "DELETE FROM sh_items WHERE id=?";
	private static final String SQL_SELECT_ITEMS_BY_DEPARTMENT = "SELECT id, name, price, amount, min_amount FROM sh_items WHERE department_id=?";
	private static final String SQL_SELECT_ITEMS_BY_DEPARTMENT_DEPLETING =  SQL_SELECT_ITEMS_BY_DEPARTMENT + " AND amount < min_amount";
	private static final String SQL_SELECT_ITEMS_BY_SUPPLIER_WITH_LAST_PRICE = "SELECT i.id, name, price, amount, min_amount, " +
			"(SELECT price FROM sh_supplies_items si INNER JOIN sh_supplies s ON si.supply_id=s.id " +
			"WHERE s.supplier_id=? AND si.item_id=i.id AND s.date=(SELECT MAX(s1.date) FROM sh_supplies_items si1 INNER JOIN sh_supplies s1 ON si1.supply_id=s1.id " +
			"WHERE s1.supplier_id=? AND si1.item_id=i.id)) AS last_price " +
			"FROM sh_items i INNER JOIN sh_suppliers_items sri ON i.id=sri.item_id WHERE sri.supplier_id=?";

	private QueryRunner queryRunner;

	public ItemDaoImpl(QueryRunner queryRunner) {
		this.queryRunner = queryRunner;
	}

	@Override
	public Item find(Long key) {
		return null;
	}

	@Override
	public List<Item> findAll() {
		try {
			return queryRunner.query(SQL_SELECT_ITEMS, new ItemWithDepartmentListHandler());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void persist(Item item) {
		try {
			Long id = queryRunner.insert(SQL_INSERT_ITEM, new LongHandler(), item.getName(),
					item.getPrice(), item.getAmount(), item.getMinAmount(), item.getDepartment().getId());
			item.setId(id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(Item item) {
		try {
			queryRunner.update(SQL_UPDATE_ITEM, item.getName(),
					item.getPrice(), item.getAmount(), item.getMinAmount());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			queryRunner.update(SQL_DELETE_ITEM, id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ItemPrice> findBySupplierWithLastPrice(Supplier supplier) {
		try {
			return queryRunner.query(SQL_SELECT_ITEMS_BY_SUPPLIER_WITH_LAST_PRICE, new ItemPriceListHandler(), supplier.getId());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Item> findByDepartment(Department department) {
		try {
			return queryRunner.query(SQL_SELECT_ITEMS_BY_DEPARTMENT, new ItemListHandler(), department.getId());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Item> findByDepartmentDepleting(Department department) {
		try {
			return queryRunner.query(SQL_SELECT_ITEMS_BY_DEPARTMENT_DEPLETING, new ItemListHandler(), department.getId());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	private static class ItemListHandler implements ResultSetHandler<List<Item>> {

		@Override
		public List<Item> handle(ResultSet rs) throws SQLException {
			List<Item> items = new ArrayList<Item>();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getLong("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getLong("price"));
				item.setAmount(rs.getLong("amount"));
				item.setMinAmount(rs.getLong("min_amount"));
			}
			return items;
		}
	}

	private static class ItemWithDepartmentListHandler implements ResultSetHandler<List<Item>> {

		@Override
		public List<Item> handle(ResultSet rs) throws SQLException {
			Map<Long, Department> deps = new HashMap<>();
			List<Item> items = new ArrayList<Item>();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getLong("i_id"));
				item.setName(rs.getString("i_name"));
				item.setPrice(rs.getLong("price"));
				item.setAmount(rs.getLong("amount"));
				item.setMinAmount(rs.getLong("min_amount"));

				Long dep_id = rs.getLong("department_id");
				Department dep = deps.get(dep_id);
				if (dep == null) {
					dep = new Department(rs.getLong("d_id"), rs.getString("d_name"));
					deps.put(dep_id, dep);
				}
				item.setDepartment(dep);

				items.add(item);
			}
			return items;
		}
	}

	private static class ItemPriceListHandler implements ResultSetHandler<List<ItemPrice>> {

		@Override
		public List<ItemPrice> handle(ResultSet rs) throws SQLException {
			List<ItemPrice> items = new ArrayList<>();
			while (rs.next()) {
				ItemPrice itemPrice = new ItemPrice();
				Item item = new Item();
				item.setId(rs.getLong("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getLong("price"));
				item.setAmount(rs.getLong("amount"));
				item.setMinAmount(rs.getLong("min_amount"));
				itemPrice.setPrice(rs.getLong("last_price"));

				items.add(itemPrice);
			}
			return items;
		}
	}
	
}
