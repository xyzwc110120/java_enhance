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

    /*
        定长与可变长：
            首先从字节上来说CHAR是定长，意思就是只要输入在我这个定长以下，不管是几个字符，它的实际占用空间都是CHAR定长的长度。
        而VARCHAR则相对来说会节省一点空间，比如：你VARCHAR的长度设为10，那么你只存储了两个字符长度，那么最终占用的空间也就是两个字符长度。

            还有一点就是关于用途：VARCHAR虽然比CHAR节省空间，但是如果一个VARCHAR列经常被修改，而且每次被修改的数据的长度不同，
        这会引起‘行迁移’(Row Migration)现象，这会造成多余的I/O，是数据库设计和调整中要尽力避免的，在这种情况下用CHAR代替VARCHAR会更好一些。
        比如：存储用户密码，MD5或者serialize加密之后的长度为32或者16，密码字段的类型就可以设置为CHAR定长32或者16
    */
}
