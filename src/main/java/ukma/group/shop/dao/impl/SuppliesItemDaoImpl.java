package ukma.group.shop.dao.impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import ukma.group.shop.dao.DaoException;
import ukma.group.shop.dao.LongHandler;
import ukma.group.shop.dao.SuppliesItemDao;
import ukma.group.shop.dao.SuppliesItemKey;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.entity.SuppliesItem;
import ukma.group.shop.entity.Supply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliesItemDaoImpl implements SuppliesItemDao {

    private static final String SQL_SELECT_SUPPLIES_ITEMS = "SELECT * FROM sh_supplies_items s";
    private static final String SQL_SELECT_SUPPLIES_ITEM_BY_ID = SQL_SELECT_SUPPLIES_ITEMS + " WHERE supply_id = ? and item_id = ?";
    private static final String SQL_INSERT_SUPPLIES_ITEM =  "INSERT INTO sh_supplies_items (amount, price) VALUES (?, ?)";
    private static final String SQL_UPDATE_SUPPLIES_ITEM = "UPDATE sh_supplies_items SET amount = ?, price = ? WHERE supply_id = ? and item_id = ?";
    private static final String SQL_DELETE_SUPPLIES_ITEM = "DELETE FROM sh_supplies_items WHERE supply_id = ? and item_id = ?";

    private QueryRunner queryRunner;

    public SuppliesItemDaoImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public SuppliesItem find(SuppliesItemKey key){
        try {
//            return queryRunner.query(SQL_SELECT_SUPPLIES_ITEM_BY_ID, new BeanHandler<>(SuppliesItem.class), id);
            return queryRunner.query(
                    SQL_SELECT_SUPPLIES_ITEM_BY_ID,
                    SUPPLIES_ITEM_LIST_RESULT_SET_HANDLER,
                    key.getSupplyId(), key.getItemId()
            ).iterator().next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<SuppliesItem> findAll() {
        try {
//            return queryRunner.query(SQL_SELECT_SUPPLIES_ITEMS, new BeanListHandler<>(SuppliesItem.class));
            return queryRunner.query(SQL_SELECT_SUPPLIES_ITEMS, SUPPLIES_ITEM_LIST_RESULT_SET_HANDLER);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void persist(SuppliesItem suppliesItem) {
        try {
            SuppliesItemKey key = queryRunner.insert(
                    SQL_INSERT_SUPPLIES_ITEM,
                    SUPPLIES_ITEM_KEY_RESULT_SET_HANDLER,
                    suppliesItem.getAmount(),
                    suppliesItem.getPrice()
            );

            Supply supply = new Supply();
            supply.setId(key.getSupplyId());
            suppliesItem.setSupply(supply);

            Item item = new Item();
            item.setId(key.getItemId());
            suppliesItem.setItem(item);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(SuppliesItem supply) {
        try {
            queryRunner.update(
                    SQL_UPDATE_SUPPLIES_ITEM,
                    supply.getAmount(),
                    supply.getPrice(),
                    supply.getSupply().getId(),
                    supply.getItem().getId()
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(SuppliesItemKey key) {
        try {
            queryRunner.update(SQL_DELETE_SUPPLIES_ITEM, key.getSupplyId(), key.getItemId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static final ResultSetHandler<List<SuppliesItem>> SUPPLIES_ITEM_LIST_RESULT_SET_HANDLER = new ResultSetHandler<List<SuppliesItem>>() {
        @Override
        public List<SuppliesItem> handle(ResultSet rs) throws SQLException {
            List<SuppliesItem> suppliesItems = new ArrayList<>();
            while(rs.next()) {
                SuppliesItem suppliesItem = new SuppliesItem();
                Supply supply = new Supply();
                supply.setId(rs.getLong("supply_id"));
                suppliesItem.setSupply(supply);

                Item item = new Item();
                item.setId(rs.getLong("item_id"));
                suppliesItem.setItem(item);

                suppliesItem.setAmount(rs.getInt("amount"));
                suppliesItem.setPrice(rs.getDouble("price"));

                suppliesItems.add(suppliesItem);
            }
            return suppliesItems;
        }


    };

    private static final ResultSetHandler<SuppliesItemKey> SUPPLIES_ITEM_KEY_RESULT_SET_HANDLER = new ResultSetHandler<SuppliesItemKey>() {

        @Override
        public SuppliesItemKey handle(ResultSet rs) throws SQLException {
            return new SuppliesItemKey(rs.getLong("supplier_id"), rs.getLong("item_id"));
        }
    };


}
