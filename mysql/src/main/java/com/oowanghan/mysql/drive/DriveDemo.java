package com.oowanghan.mysql.drive;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

/**
 * @Author WangHan
 * @Create 2022/4/23 1:02 下午
 */
public class DriveDemo {

    public static void main(String[] args) throws SQLException {
        // 加载Class到AppClassLoader（系统类加载器），然后注册驱动类

        // 从Java1.6开始自带的jdbc4.0版本已支持SPI服务加载机制，只要mysql的jar包在类路径中，就可以注册mysql驱动。
        // 故不需要通过Class.forName()来注册mysql驱动
        // Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/testdb";
        // 通过java库获取数据库连接
        Connection conn = java.sql.DriverManager.getConnection(url, "name", "password");
    }
}
