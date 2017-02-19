package ukma.group.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.ItemDao;
import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;

public class ItemDaoImpl implements ItemDao {
	
	static final String SQL_SELECT_ITEMS = 
			"SELECT i.id AS i_id, i.name AS i_name, price, amount, min_amount, department_id, d.id AS d_id, d.name AS d_name  "
			+ "FROM sh_items i "
			+ "INNER JOIN sh_departments d ON i.department_id = d.id";
	
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

			Map<Long, Department> deps = new HashMap<>();
			
			List<Item> items = new ArrayList<Item>();
			while (rs.next())
			{
				Item item = new Item();
				item.setId(rs.getLong("i_id"));
				item.setName(rs.getString("i_name"));
				item.setPrice(rs.getLong("price"));
				item.setAmount(rs.getLong("amount"));
				item.setMinAmount(rs.getLong("min_amount"));
				
				Long dep_id = rs.getLong("department_id");
				Department dep = deps.get(dep_id);
				
				if (dep == null)
				{
					dep = new Department(rs.getLong("d_id"), rs.getString("d_name"));
					deps.put(dep_id, dep);
				}
				
				item.setDepartment(dep);
				
				items.add(item);
			}
			return items;
		}
		
	}
	
}
