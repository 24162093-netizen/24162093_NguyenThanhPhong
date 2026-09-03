package vn.test;

import java.sql.Connection;

import vn.connection.DBconnection;

public class Testconnection {

    public static void main(String[] args) {

        try {

            Connection conn =
                    new DBconnection().getConnection();

            if (conn != null) {
                System.out.println(
                    "KET NOI MYSQL THANH CONG!"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}