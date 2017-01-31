package ukma.group.shop.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ukma.group.shop.dao.SupplierDao;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.LongHandler;

import java.sql.SQLException;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {

    private static final String SQL_SELECT_SUPPLIERS = "SELECT id, name FROM sh_suppliers s";
    private static final String SQL_SELECT_SUPPLIER_BY_ID = SQL_SELECT_SUPPLIERS + " WHERE id=?";
    private static final String SQL_INSERT_SUPPLIER =  "INSERT INTO sh_suppliers (name) VALUES (?)";
    private static final String SQL_UPDATE_SUPPLIER = "UPDATE sh_suppliers SET name=? WHERE id=?";
    private static final String SQL_DELETE_SUPPLIER = "DELETE FROM sh_suppliers WHERE id=?";
    private static final String SQL_SELECT_SUPPLIERS_BY_ITEM = SQL_SELECT_SUPPLIERS
            + " INNER JOIN sh_suppliers_items si ON s.id=si.supplier_id"
            + " WHERE si.item_id=?";

    private QueryRunner queryRunner;

    public SupplierDaoImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public Supplier find(Long id) {
        try {
            return queryRunner.query(SQL_SELECT_SUPPLIER_BY_ID, new BeanHandler<>(Supplier.class), id);
        } catch (SQLException e) {
           throw new DaoException(e);
        }
    }

    @Override
    public List<Supplier> findAll() {
        try {
            return queryRunner.query(SQL_SELECT_SUPPLIERS, new BeanListHandler<>(Supplier.class));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void persist(Supplier supplier) {
        try {
            Long id = queryRunner.insert(SQL_INSERT_SUPPLIER, new LongHandler(), supplier.getName());
            supplier.setId(id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Supplier supplier) {
        try {
            queryRunner.update(SQL_UPDATE_SUPPLIER, supplier.getName(), supplier.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            queryRunner.update(SQL_DELETE_SUPPLIER, id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Supplier> findByItem(Item item) {
        try {
            return queryRunner.query(SQL_SELECT_SUPPLIERS_BY_ITEM, new BeanListHandler<>(Supplier.class), item.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
