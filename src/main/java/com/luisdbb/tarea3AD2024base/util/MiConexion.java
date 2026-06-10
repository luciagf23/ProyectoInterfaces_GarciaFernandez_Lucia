package com.luisdbb.tarea3AD2024base.util;


import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MiConexion {

    private static DataSource dataSource;

    static {
        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/bdtarea3ad?useSSL=false&serverTimezone=UTC");
            ds.setUser("root");
            ds.setPassword(""); 

            dataSource = ds;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
