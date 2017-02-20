package ukma.group.shop.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.LongHandler;
import ukma.group.shop.dao.OrderDao;
import ukma.group.shop.entity.Employee;
import ukma.group.shop.entity.Order;
import ukma.group.shop.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final String SQL_SELECT_ORDERS = "SELECT e.id AS e_id, date, employee_id, supplier_id, supply_id, e.name AS e_name, s.name AS s_name" +
            "FROM sh_orders o INNER JOIN sh_employees e ON o.employee_id=e.id INNER JOIN sh_suppliers s ON o.supplier_id=s.id";
    private static final String SQL_INSERT_ORDER = "INSERT INTO sh_orders (date, employee_id, supplier_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE sh_orders SET supply_id=? WHERE id=?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM sh_orders WHERE id=?";

    private QueryRunner queryRunner;

    public OrderDaoImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public Order find(Long key) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        try {
            return queryRunner.query(SQL_SELECT_ORDERS, new OrderListHandler());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void persist(Order order) {
        try {
            Long id = queryRunner.insert(SQL_INSERT_ORDER, new LongHandler(), order.getDate(), order.getEmployee().getId(), order.getSupplier().getId());
            order.setId(id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Order order) {
        try {
            queryRunner.update(SQL_UPDATE_ORDER, order.getSupply().getId(), order.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            queryRunner.update(SQL_DELETE_ORDER, id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static class OrderListHandler implements ResultSetHandler<List<Order>> {

        @Override
        public List<Order> handle(ResultSet rs) throws SQLException {
            List<Order> orders = new ArrayList<>();
            while(rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("e_id"));
                order.setDate(rs.getDate("date"));
                Employee employee = new Employee();
                employee.setId(rs.getLong("employee_id"));
                employee.setName(rs.getString("e_name"));
                order.setEmployee(employee);
                Supplier supplier = new Supplier();
                supplier.setId(rs.getLong("supplier_id"));
                supplier.setName(rs.getString("s_name"));
                order.setSupplier(supplier);
                orders.add(order);
            }
            return orders;
        }
    }
}
