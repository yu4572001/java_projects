package com.yystudy.mhl.dao;

import com.yystudy.mhl.utils.JDBCbyDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDao<T> {
    QueryRunner queryRunner = new QueryRunner();

    //编写所有的dao都通用的方法
    //通用dml操作方法
    public int update(String sql, Object... parameters){
        Connection connection = null;

        //从连接池中得到一个连接
        connection = JDBCbyDruid.getConnectiont();
        try {
            int update = queryRunner.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCbyDruid.closeConnection(connection);
        }

    }


    //编写返回多行多列的查询记录 BeanListHandler
    /**
     *
     * @param sql  传入的语句
     * @param clazz   传入一个类的Class对象(反射)，即要将结果集中的记录保存为该对象添加进集合中
     * @param parameters    传入的sql语句中的？参数
     */
    public List<T> queryMany(String sql,Class<T> clazz, Object... parameters){
        Connection connection = null;

        //得到池中的一个连接
        connection = JDBCbyDruid.getConnectiont();
        try {
            List<T> query = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCbyDruid.closeConnection(connection);
        }

    }

    //编写返回单行多列的查询记录 BeanHandler
    public T querySingle(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;

        //得到连接池的连接
        connection = JDBCbyDruid.getConnectiont();

        try {
            T query = queryRunner.query(connection, sql, new BeanHandler<>(clazz), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCbyDruid.closeConnection(connection);
        }

    }

    //编写返回单行单列的查询记录 ScalarHandler
    public Object queryScalar(String sql, Object... parameters){
        Connection connection = null;

        //得到连接池的连接
        connection = JDBCbyDruid.getConnectiont();
        try {
            Object query = queryRunner.query(connection, sql, new ScalarHandler<>(), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCbyDruid.closeConnection(connection);
        }
    }


}
