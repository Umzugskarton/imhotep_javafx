package database.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBmySQL implements DB {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String jdbcUrlPrefix = "jdbc:mysql://";

    DBmySQL(){

    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getJdbcUrlPrefix() {
        return jdbcUrlPrefix;
    }
}
