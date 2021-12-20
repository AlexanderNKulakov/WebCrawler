package com.luxoft.training.akka.webcrawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Orkhan Gasimov
 */
public class H2Helper {
    private static final Connection conn;
    static {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS keywords (url VARCHAR(250), keyword VARCHAR(250))");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void persistKeyword(String url, String keyword) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO keywords (url, keyword) VALUES (?, ?)")) {
            stmt.setString(1, url);
            stmt.setString(2, keyword);
            stmt.executeUpdate();
        }
    }

    public static Map<String, String> searchKeyword(String keyword) throws SQLException {
        Map<String, String> map = new HashMap<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT keyword, url FROM keywords WHERE lower(keyword) LIKE ?")) {
            stmt.setString(1, "%" + keyword.toLowerCase() + "%");
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    map.put(rset.getString(1), rset.getString(2));
                }
            }
        }
        return map;
    }
}
