package com.joe.homework.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class GPSimpleExecutor implements GPExecutor {

    public <T> T query(String statement, String parameter) {
        return getOne(getConn(), statement, parameter);
    }

    private static Connection getConn() {
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

    /**
     * reference to https://www.cnblogs.com/wuyuegb2312/p/3872607.html
     */
    private static <T>T getOne(Connection conn, String statement, String parameter) {
        String sql = statement;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Test test = null;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(String.format(sql, Integer.valueOf(parameter)));
            rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                test = new Test();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatementClose(pstmt);
            resultSetClose(rs);
            connectionClose(conn);
        }
        return (T)test;
    }

    private static void preparedStatementClose(PreparedStatement pstmt){
        try {
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void resultSetClose(ResultSet rs){
        try {
            rs.close();
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