package ukma.group.shop.dao;

import javax.sql.DataSource;

public interface DataSourceFactory {

    DataSource getDataSource(String driverClassName, String url, String username, String password);

}
