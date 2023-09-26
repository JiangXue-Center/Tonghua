package com.hf.artwork.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstWordTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        // 将字符串按逗号分隔并取第一个单词，然后设置到PreparedStatement中
        String[] words = parameter.split(",");
        if (words.length > 0) {
            ps.setString(i, words[0]);
        } else {
            ps.setString(i, null);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return extractFirstWord(value);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return extractFirstWord(value);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return extractFirstWord(value);
    }

    private String extractFirstWord(String value) {
        if (value != null) {
            String[] words = value.split(",");
            if (words.length > 0) {
                return words[0];
            }
        }
        return null;
    }
}
