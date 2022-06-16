package com.offcn.dao.impl;

import com.offcn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {

    private QueryRunner queryRunner =  new QueryRunner(JDBCUtils.getDataSource());

    /**
     * 通用的增删改
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql , Object... params) {

        int rows = 0;
        try {
            rows = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     *
     * 通用的查询多条记录的操作
     * @param clazz
     * @param sql
     * @param params
     * @return
     */
    public List<T> queryList(Class<T> clazz , String sql , Object... params) {
        List<T> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<T>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * 查一条数据的通用操作
     * @param clazz
     * @param sql
     * @param params
     * @return
     */
    public T queryOne(Class<T> clazz , String sql,Object... params) {
        T t = null;
        try {
            t = queryRunner.query(sql, new BeanHandler<T>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public Object queryScale(String sql , Object... params) {
        Object o = null;
        try {
            o = queryRunner.query(sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }
}
