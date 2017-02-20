package ukma.group.shop.dao.impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.LongHandler;
import ukma.group.shop.dao.SupplyDao;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.entity.Supply;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplyDaoImpl implements SupplyDao {

    private static final String SQL_SELECT_SUPPLIES = "SELECT * FROM sh_supplies s";
    private static final String SQL_SELECT_SUPPLY_BY_ID = SQL_SELECT_SUPPLIES + " WHERE id = ?";
    private static final String SQL_INSERT_SUPPLY =  "INSERT INTO sh_supplies (date, supplier_id) VALUES (?, ?)";
    private static final String SQL_UPDATE_SUPPLY = "UPDATE sh_supplies SET date = ?, supplier_id = ? WHERE id = ?";
    private static final String SQL_DELETE_SUPPLY = "DELETE FROM sh_supplies WHERE id = ?";

    private QueryRunner queryRunner;

    public SupplyDaoImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public Supply find(Long id){
        try {
//            return queryRunner.query(SQL_SELECT_SUPPLY_BY_ID, new BeanHandler<>(Supply.class), id);
            return queryRunner.query(
                    SQL_SELECT_SUPPLY_BY_ID,
                    SUPPLY_LIST_RESULT_SET_HANDLER,
                    id
            ).iterator().next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Supply> findAll() {
        try {
//            return queryRunner.query(SQL_SELECT_SUPPLIES, new BeanListHandler<>(Supply.class));
            return queryRunner.query(SQL_SELECT_SUPPLIES, SUPPLY_LIST_RESULT_SET_HANDLER);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void persist(Supply supply) {
        try {
            Long id = queryRunner.insert(SQL_INSERT_SUPPLY, new LongHandler(), supply.getDate(), supply.getSupplier().getId());
            supply.setId(id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Supply supply) {
        try {
            queryRunner.update(SQL_UPDATE_SUPPLY, supply.getDate(), supply.getSupplier().getId(), supply.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            queryRunner.update(SQL_DELETE_SUPPLY, id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static final ResultSetHandler<List<Supply>> SUPPLY_LIST_RESULT_SET_HANDLER = new ResultSetHandler<List<Supply>>() {
        @Override
        public List<Supply> handle(ResultSet rs) throws SQLException {
            List<Supply> supplies = new ArrayList<>();
            while(rs.next()) {
                Supply supply = new Supply();
                supply.setId(rs.getLong("id"));
                supply.setDate(rs.getTimestamp("date"));
                Supplier supplier = new Supplier();
                supplier.setId(rs.getLong("supplier_id"));
                supply.setSupplier(supplier);
                supplies.add(supply);
            }
            return supplies;
        }
    };

}
