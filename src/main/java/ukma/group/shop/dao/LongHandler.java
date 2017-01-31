package ukma.group.shop.dao;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LongHandler implements ResultSetHandler<Long> {

    @Override
    public Long handle(ResultSet rs) throws SQLException {
        return rs.next() ? rs.getLong(1): null;
    }
}
