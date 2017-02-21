package ukma.group.shop.dao;

import org.apache.commons.dbutils.QueryRunner;

import ukma.group.shop.dao.impl.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * <code>DaoManager</code> is a global manager for all entity-specific Dao objects.
 */
public class DaoManager {

    private static final String JDBC_PROPERTIES_PATH = "properties/jdbc.properties";

    private static DaoManager instance = new DaoManager();

    private DataSource dataSource;
    private QueryRunner queryRunner;

    private SupplierDao supplierDao;
    private ItemDao itemDao;
    private DepartmentDao departmentDao;
    private SupplyDao supplyDao;
    private SuppliesItemDao suppliesItemDao;
    private OrderDao orderDao;
    //other dao

    public static DaoManager getInstance() {
        return instance;
    }

    private DaoManager() {
        initDataSource(new BasicDataSourceFactory());
        queryRunner = new QueryRunner(dataSource);
    }

    private void initDataSource(DataSourceFactory factory) {
        Properties props = new Properties();
        try {
            props.load(DaoManager.class.getClassLoader().getResourceAsStream(JDBC_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        dataSource = factory.getDataSource(
                props.getProperty("jdbc.driverClassName"),
                props.getProperty("jdbc.url"),
                props.getProperty("jdbc.username"),
                props.getProperty("jdbc.password"));
    }

    public SupplierDao getSupplierDao() {
        if(supplierDao == null) {
            supplierDao = new SupplierDaoImpl(queryRunner);
        }
        return supplierDao;
    }

    public ItemDao getItemDao() {
        if(itemDao == null) {
            itemDao = new ItemDaoImpl(queryRunner);
        }
        return itemDao;
    }

    public DepartmentDao getDepartmentDao() {
        if(departmentDao == null) {
        	departmentDao = new DepartmentDaoImpl(queryRunner);
        }
        return departmentDao;
    }

    public SupplyDao getSupplyDao() {
        if(supplyDao == null) {
            supplyDao = new SupplyDaoImpl(queryRunner);
        }
        return supplyDao;
    }

    public SuppliesItemDao getSuppliesItemDao() {
        if(suppliesItemDao == null) {
            suppliesItemDao = new SuppliesItemDaoImpl(queryRunner);
        }
        return suppliesItemDao;
    }
    public OrderDao getOrderDao() {
        if(orderDao == null) {
            orderDao = new OrderDaoImpl(queryRunner);
        }
        return orderDao;
    }
}
