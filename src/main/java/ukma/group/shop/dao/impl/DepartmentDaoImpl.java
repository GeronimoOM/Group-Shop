package ukma.group.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.DepartmentDao;
import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final String SQL_SELECT_DEPS = "SELECT id, name FROM sh_departments d";


    private QueryRunner queryRunner;

    public DepartmentDaoImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

	@Override
	public Department find(Long key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
        try {
            return queryRunner.query(SQL_SELECT_DEPS, new DepartmentListHandler());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
	}

	@Override
	public void persist(Department entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long key) {
		// TODO Auto-generated method stub
		
	}

	private static class DepartmentListHandler implements ResultSetHandler<List<Department>>
	{
		@Override
		public List<Department> handle(ResultSet rs) throws SQLException {

			List<Department> deps = new ArrayList<Department>();
			while (rs.next())
			{
				Department dep = new Department(rs.getLong("id"), rs.getString("name"));
				
				deps.add(dep);
			}
			return deps;
		}
		
	}

}
