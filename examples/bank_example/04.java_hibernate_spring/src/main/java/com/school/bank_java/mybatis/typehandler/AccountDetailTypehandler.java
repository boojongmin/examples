package com.school.bank_java.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.school.bank_java.code.AccountDetailType;

public class AccountDetailTypehandler implements TypeHandler<AccountDetailType> {

	@Override
	public void setParameter(PreparedStatement ps, int i,
			AccountDetailType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
		
	}

	@Override
	public AccountDetailType getResult(ResultSet rs, String columnName)
			throws SQLException {
	    return AccountDetailType.valueOf(rs.getInt(columnName));
	}

	@Override
	public AccountDetailType getResult(ResultSet rs, int columnIndex)
			throws SQLException {
	    return AccountDetailType.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public AccountDetailType getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
	    return AccountDetailType.valueOf(cs.getInt(columnIndex));
	}

}
