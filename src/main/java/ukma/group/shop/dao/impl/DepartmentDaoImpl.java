package ukma.group.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.DepartmentDao;
import ukma.group.shop.dao.LongHandler;
import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final String SQL_SELECT_DEPS = "SELECT id, name FROM sh_departments d";
	private static final String SQL_INSERT_DEP = "INSERT INTO sh_departments (name) VALUES (?)";
	private static final String SQL_UPDATE_DEP = "UPDATE sh_departments SET name=? WHERE id=?";
	private static final String SQL_DELETE_DEP = "DELETE FROM sh_departments WHERE id=?";
	private static final String SQL_SELECT_DEP_BY_ID = SQL_SELECT_DEPS + " WHERE id=?";

    private QueryRunner queryRunner;

    public DepartmentDaoImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

	@Override
	public Department find(Long key) {
		try {
			return queryRunner.query(SQL_SELECT_DEP_BY_ID, new BeanHandler<>(Department.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Department> findAll() {
        try {
            return queryRunner.query(SQL_SELECT_DEPS, new BeanListHandler<>(Department.class));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
	}

	@Override
	public void persist(Department dep) {
		try {
			Long id = queryRunner.insert(SQL_INSERT_DEP, new LongHandler(), dep.getName());
			dep.setId(id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(Department dep) {
		try {
			queryRunner.update(SQL_UPDATE_DEP, dep.getName(), dep.getId());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			queryRunner.update(SQL_DELETE_DEP, id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
