package ukma.group.shop.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * Created by Oleh on 28.01.2017.
 */
public class BasicDataSourceFactory implements DataSourceFactory {

    @Override
    public DataSource getDataSource(String driverClassName, String url, String username, String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }


}
