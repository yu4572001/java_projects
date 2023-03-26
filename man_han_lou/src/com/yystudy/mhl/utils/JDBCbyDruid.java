package com.yystudy.mhl.utils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//创建一个JDBC工具类，负责Druid连接池的建立
public class JDBCbyDruid {
    private static DataSource dataSource = null;

    //对dataSource进行初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src//druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //得到连接池中的一个连接方法
    public static Connection getConnectiont(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭，即将连接返还连接池方法
    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection){
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Statement statement, Connection connection){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
