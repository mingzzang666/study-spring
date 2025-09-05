package com.example.practice.mybatis.handler;

import com.example.practice.enumeration.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Status.class)
public class StatusHandler implements TypeHandler<Status> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Status getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) return null;

        switch (value) {
            case "active":
                return Status.ACTIVE;
            case "inactive":
                return Status.INACTIVE;
            default:
                return null;
        }
    }

    @Override
    public Status getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) return null;

        switch (value) {
            case "active":
                return Status.ACTIVE;
            case "inactive":
                return Status.INACTIVE;
            default:
                return null;
        }
    }

    @Override
    public Status getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) return null;

        switch (value) {
            case "active":
                return Status.ACTIVE;
            case "inactive":
                return Status.INACTIVE;
            default:
                return null;
        }
    }
}
