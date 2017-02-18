package ukma.group.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.ItemDao;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;

public class ItemDaoImpl implements ItemDao {
	
	static final String SQL_SELECT_ITEMS = "SELECT id, name, price, amount, min_amount, department_id FROM sh_items i";
	
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
			return queryRunner.query(SQL_SELECT_ITEMS, new ItemListHandler());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void persist(Item entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Item entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long key) {
		// TODO Auto-generated method stub
		
	}

	
	private static class ItemListHandler implements ResultSetHandler<List<Item>>
	{
		@Override
		public List<Item> handle(ResultSet rs) throws SQLException {
			List<Item> items = new ArrayList<Item>();
			while (rs.next())
			{
				Item item = new Item();
				item.setId(rs.getLong("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getLong("price"));
				item.setAmount(rs.getLong("amount"));
				item.setMinAmount(rs.getLong("min_amount"));
				item.setDepartmentId(rs.getLong("department_id"));
				
				items.add(item);
			}
			return items;
		}
		
	}
	
}
