package com.joe.handwriting.mybatis.v2.resultSetHandler;

import com.joe.handwriting.mybatis.v2.config.GPConfiguration;
import com.joe.handwriting.mybatis.v2.mapperRegistry.GPMapperRegistry;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GPResultSetHandler {
    private GPConfiguration configuration;

    public GPResultSetHandler(GPConfiguration configuration) {
        this.configuration = configuration;
    }

    public <T> T handle(PreparedStatement preparedStatement, GPMapperRegistry.MethodMapperData mapperData) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ResultSet rs = null;
        //通过mybatis的对象工厂，构建一个指定类的空对象。方便后述代码把rs值set进该对象
        Object resultObj = new DefaultObjectFactory().create(mapperData.getResultType());
        try {
            rs = preparedStatement.getResultSet();
            if (rs.next()) {
                //结果集映射类的每一个字段都一一分别设置
                for(Field field : resultObj.getClass().getDeclaredFields()){
                    setValue(resultObj, field, rs);
                }
            }
        }finally {
            resultSetClose(rs);
        }
        return (T) resultObj;
    }

    /**
     * setValue 映射方式为pojo设置
     *
     * 获得执行名字的方法,setName(parameterClazz)
     * 执行字段setter，指定对象实例中的，setter的值(/参数)
     * @param resultObj
     * @param field
     * @param rs
     */
    private void setValue(Object resultObj, Field field, ResultSet rs) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Method setMethod = resultObj.getClass().getMethod("set"+upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, handle(field, rs));
    }

    /**
     * 选择使用rs.get结果的返回类型
     * @param field
     * @param rs
     * @return
     * @throws SQLException
     */
    private Object handle(Field field, ResultSet rs) throws SQLException {
        //type Handler 的提取与完善
        Class type = field.getType();
        if(type == String.class){
            return rs.getString(field.getName());
        }
        if(type == Integer.class){
            return rs.getInt(field.getName());
        }
        return rs.getString(field.getName());
    }

    /**
     * 大写化单词的首字母
     * @param name
     * @return
     */
    private String upperCapital(String name){
        String first = name.substring(0, 1).toUpperCase();
        String tail = name.substring(1);
        return first + tail;
    }

    private static void resultSetClose(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
