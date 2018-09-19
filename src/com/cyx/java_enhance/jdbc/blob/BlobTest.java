package com.cyx.java_enhance.jdbc.blob;

import com.cyx.java_enhance.jdbc.daoDemo.util.JDBCUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BlobTest {

    @Test
    @DisplayName("存储二进制文件")
    void testSave() {
        String sql = "INSERT INTO img (img) VALUES (?)";
        Connection connection = null;
        // 这里不使用 Statement 是因为不能直接把二进制文件拼到 SQL 语句里
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setBlob(1, new FileInputStream(
                    "D:/zebra/resources/album/0ac0a5bd-ddca-46c0-8d4d-8dbe42a972db.jpg"));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement);
        }
    }

    @Test
    void testGet() {
        String sql = "SELECT i.img FROM img i WHERE i.`id` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = JDBCUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setLong(1, 1L);
            set = statement.executeQuery();
            if (set.next()) {
                // 文件拷贝
                Files.copy(set.getBlob("img").getBinaryStream(), Paths.get("D:/123.jpg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, set);
        }
    }

}
