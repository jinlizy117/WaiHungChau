package com.joe.homework.v2.statementHandler;

import com.joe.homework.v2.config.GPConfiguration;
import com.joe.homework.v2.mapperRegistry.GPMapperRegistry;
import com.joe.homework.v2.resultSetHandler.GPResultSetHandler;

import java.sql.*;

public class GPStatementHandler {
    private GPConfiguration configuration;

    public GPStatementHandler(GPConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     *
     * @param mapperData
     * @param parameter 参数暂时只接受单个parameter的整型数字的String类型
     * @param <T>
     * @return
     */
    public <T>T handler(GPMapperRegistry.MethodMapperData mapperData, Object parameter) throws Exception {
        GPResultSetHandler resultSetHandler = new GPResultSetHandler(configuration);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            //可更改用：PreparedStatementHandler
            preparedStatement = connection.prepareStatement(String.format(mapperData.getSql(), Integer.parseInt(String.valueOf(parameter))));
            preparedStatement.executeQuery();
            return resultSetHandler.handle(preparedStatement, mapperData);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatementClose(preparedStatement);
            connectionClose(connection);
        }
        return null;
    }


    private static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/gp";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void preparedStatementClose(PreparedStatement pstmt){
        try {
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void connectionClose(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
